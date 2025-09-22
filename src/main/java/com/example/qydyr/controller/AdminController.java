package com.example.qydyr.controller;

import com.example.qydyr.model.Afisha;
import com.example.qydyr.model.Place;
import com.example.qydyr.model.User;
import com.example.qydyr.service.AfishaService;
import com.example.qydyr.service.PlaceService;
import com.example.qydyr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPathProperty;

    @Autowired
    private AfishaService afishaService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String adminDashboard(Model model) {
        // Получаем статистику
        long totalAfisha = afishaService.count();
        long totalPlaces = placeService.count();
        long totalUsers = userService.count();
        
        model.addAttribute("totalAfisha", totalAfisha);
        model.addAttribute("totalPlaces", totalPlaces);
        model.addAttribute("totalUsers", totalUsers);
        
        return "admin/dashboard";
    }

    @GetMapping("/login")
    public String adminLogin() {
        return "admin/login";
    }

    // ========== AFISHA MANAGEMENT ==========
    
    @GetMapping("/afisha")
    public String afishaList(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "id") String sortBy,
                           @RequestParam(defaultValue = "desc") String sortDir,
                           Model model) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<Afisha> afishaPage = afishaService.getAllAfisha(pageable);
        model.addAttribute("afishaPage", afishaPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", afishaPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        
        return "admin/afisha/list";
    }

    @GetMapping("/afisha/new")
    public String newAfishaForm(Model model) {
        model.addAttribute("afisha", new Afisha());
        model.addAttribute("places", placeService.getAll());
        return "admin/afisha/form";
    }

    @GetMapping("/afisha/edit/{id}")
    public String editAfishaForm(@PathVariable Long id, Model model) {
        Afisha afisha = afishaService.getAfishaById(id).orElse(null);
        if (afisha == null) {
            return "redirect:/admin/afisha";
        }
        model.addAttribute("afisha", afisha);
        model.addAttribute("places", placeService.getAll());
        return "admin/afisha/form";
    }

    @PostMapping("/afisha/save")
public String saveAfisha(
        @ModelAttribute Afisha afisha,
        @RequestParam("imageFile") MultipartFile imageFile,
        RedirectAttributes redirectAttributes) {
    try {
        System.out.println("=== DEBUG: AdminController saveAfisha ===");
        System.out.println("Afisha name: " + afisha.getName());
        System.out.println("Image file: " + (imageFile != null ? imageFile.getOriginalFilename() : "null"));
        System.out.println("Image file empty: " + (imageFile != null ? imageFile.isEmpty() : "null"));
        System.out.println("Upload path property: " + uploadPathProperty);
        
        if (imageFile != null && !imageFile.isEmpty()) {
            // Сохраняем файл в папку uploads/afisha
            // Извлекаем расширение файла
            String originalFilename = imageFile.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            // Генерируем безопасное имя файла только с UUID и расширением
            String fileName = UUID.randomUUID().toString() + fileExtension;
            System.out.println("Original filename: " + originalFilename);
            System.out.println("Generated filename: " + fileName);
            
            Path uploadPath = Paths.get(uploadPathProperty + "/afisha");
            System.out.println("Upload path: " + uploadPath.toString());
            System.out.println("Upload path exists: " + Files.exists(uploadPath));
            
            if (!Files.exists(uploadPath)) {
                System.out.println("Creating upload directory: " + uploadPath);
                Files.createDirectories(uploadPath);
            }
            
            Path filePath = uploadPath.resolve(fileName);
            System.out.println("Full file path: " + filePath.toString());
            
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved successfully");

            // Сохраняем путь к файлу в сущность
            String imagePath = "/uploads/afisha/" + fileName;
            afisha.setImagePath(imagePath);
            System.out.println("Image path set to: " + imagePath);
        } else {
            System.out.println("No image file provided or file is empty");
        }

        afishaService.saveAfisha(afisha);
        redirectAttributes.addFlashAttribute("successMessage", "Афиша успешно сохранена!");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при сохранении афиши: " + e.getMessage());
    }
    return "redirect:/admin/afisha";
}


    @PostMapping("/afisha/delete/{id}")
    public String deleteAfisha(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            afishaService.deleteAfisha(id);
            redirectAttributes.addFlashAttribute("successMessage", "Афиша успешно удалена!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при удалении афиши: " + e.getMessage());
        }
        return "redirect:/admin/afisha";
    }

    // ========== PLACE MANAGEMENT ==========
    
    @GetMapping("/places")
    public String placesList(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "id") String sortBy,
                           @RequestParam(defaultValue = "desc") String sortDir,
                           Model model) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<Place> placesPage = placeService.getAllPlaces(pageable);
        model.addAttribute("placesPage", placesPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", placesPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        
        return "admin/places/list";
    }

    @GetMapping("/places/new")
    public String newPlaceForm(Model model) {
        model.addAttribute("place", new Place());
        return "admin/places/form";
    }

    @GetMapping("/places/edit/{id}")
    public String editPlaceForm(@PathVariable Long id, Model model) {
        Place place = placeService.getPlaceById(id).orElse(null);
        if (place == null) {
            return "redirect:/admin/places";
        }
        model.addAttribute("place", place);
        return "admin/places/form";
    }

    @PostMapping("/places/save")
    public String savePlace(@ModelAttribute Place place, RedirectAttributes redirectAttributes) {
        try {
            placeService.savePlace(place);
            redirectAttributes.addFlashAttribute("successMessage", "Место успешно сохранено!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при сохранении места: " + e.getMessage());
        }
        return "redirect:/admin/places";
    }

    @PostMapping("/places/delete/{id}")
    public String deletePlace(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            placeService.deletePlace(id);
            redirectAttributes.addFlashAttribute("successMessage", "Место успешно удалено!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при удалении места: " + e.getMessage());
        }
        return "redirect:/admin/places";
    }

    @PostMapping("/places/{id}/toggle-publish")
    @ResponseBody
    public String togglePlacePublish(@PathVariable Long id) {
        try {
            Place place = placeService.getPlaceById(id).orElse(null);
            if (place != null) {
                place.setPublished(!Boolean.TRUE.equals(place.getPublished()));
                placeService.savePlace(place);
                return "success";
            }
            return "error";
        } catch (Exception e) {
            return "error";
        }
    }

    // ========== USER MANAGEMENT ==========
    
    @GetMapping("/users")
    public String usersList(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          @RequestParam(defaultValue = "id") String sortBy,
                          @RequestParam(defaultValue = "desc") String sortDir,
                          Model model) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<User> usersPage = userService.getAllUsers(pageable);
        model.addAttribute("usersPage", usersPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        
        return "admin/users/list";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin/users";
        }
        model.addAttribute("user", user);
        return "admin/users/form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Пользователь успешно обновлен!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "Пользователь успешно удален!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при удалении пользователя: " + e.getMessage());
        }
        return "redirect:/admin/users";
    }
}
