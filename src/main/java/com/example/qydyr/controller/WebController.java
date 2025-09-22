package com.example.qydyr.controller;

import com.example.qydyr.auth.AuthenticationRequest;
import com.example.qydyr.auth.AuthenticationResponse;
import com.example.qydyr.auth.AuthenticationService;
import com.example.qydyr.auth.RegisterRequest;
import com.example.qydyr.dto.UserUpdateRequest;
import com.example.qydyr.model.Afisha;
import com.example.qydyr.model.Place;
import com.example.qydyr.model.User;
import com.example.qydyr.model.enums.EventCategory;
import com.example.qydyr.model.enums.Status;
import com.example.qydyr.service.AfishaService;
import com.example.qydyr.service.PaymentService;
import com.example.qydyr.service.PlaceService;
import com.example.qydyr.repository.OrderRepository;
import com.example.qydyr.model.Order;
import com.example.qydyr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final AuthenticationService authenticationService;
    private final AfishaService afishaService;
    private final PlaceService placeService;
    private final UserService userService;
    private final PaymentService paymentService;
    private final OrderRepository orderRepository;

    @ModelAttribute("user")
    public User addUserToModel() {
        User user = getCurrentUser();
        return user;
    }

    @GetMapping("/")
    public String index(Model model) {
        // Получаем последние 6 афиш
        Pageable pageable = PageRequest.of(0, 6);
        Page<Afisha> recentAfisha = afishaService.getAllAfisha(pageable);
        
        // DEBUG: Логируем информацию о последних афишах и их изображениях
        System.out.println("=== DEBUG: WebController index ===");
        System.out.println("Recent afisha count: " + recentAfisha.getContent().size());
        
        for (Afisha afisha : recentAfisha.getContent()) {
            System.out.println("Recent Afisha ID: " + afisha.getId() + 
                             ", Name: " + afisha.getName() + 
                             ", ImagePath: " + afisha.getImagePath());
        }
        
        model.addAttribute("recentAfisha", recentAfisha.getContent());
        
        return "pages/index";
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error) {
        if (getCurrentUser() != null) {
            return "redirect:/";
        }
        
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        if (error != null) {
            model.addAttribute("error", "Неверный email или пароль");
        }
        
        return "pages/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        if (getCurrentUser() != null) {
            return "redirect:/";
        }
        
        model.addAttribute("registerRequest", new RegisterRequest());
        return "pages/register";
    }

    // Удаляем кастомный login метод - Spring Security будет обрабатывать формы автоматически

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterRequest request, 
                          BindingResult bindingResult, 
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerRequest", request);
            return "pages/register";
        }

        try {
            AuthenticationResponse authResponse = authenticationService.register(request);
            redirectAttributes.addFlashAttribute("message", "Регистрация успешна! Добро пожаловать!");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка регистрации: " + e.getMessage());
            model.addAttribute("registerRequest", request);
            return "pages/register";
        }
    }


    @GetMapping("/events")
    public String afishaPage(Model model, 
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "12") int size,
                            @RequestParam(required = false) String search,
                            @RequestParam(required = false) EventCategory category,
                            @RequestParam(required = false) Status status) {
        Pageable pageable = PageRequest.of(page, size);
        
        Page<Afisha> afishaPage;
        // Check if any filter is applied (non-empty search, category, or status)
        boolean hasFilters = (search != null && !search.trim().isEmpty()) || category != null || status != null;
        
        if (hasFilters) {
            // Use search and filter functionality
            afishaPage = afishaService.getAfishesBySearchAndFilter(search, category, status, pageable);
        } else {
            // Use default pagination
            afishaPage = afishaService.getAllAfisha(pageable);
        }
        
        // DEBUG: Логируем информацию об афишах и их изображениях
        System.out.println("=== DEBUG: WebController afishaPage ===");
        System.out.println("Total afisha count: " + afishaPage.getTotalElements());
        System.out.println("Current page afisha count: " + afishaPage.getContent().size());
        
        for (Afisha afisha : afishaPage.getContent()) {
            System.out.println("Afisha ID: " + afisha.getId() + 
                             ", Name: " + afisha.getName() + 
                             ", ImagePath: " + afisha.getImagePath());
        }
        
        model.addAttribute("afishaPage", afishaPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", afishaPage.getTotalPages());
        model.addAttribute("search", search);
        model.addAttribute("category", category);
        model.addAttribute("status", status);
        
        return "pages/afisha";
    }

    @GetMapping("/events/{id}")
    public String afishaDetailPage(@PathVariable Long id, Model model, Authentication authentication) {
        try {
            Afisha afisha = afishaService.getById(id);
            if (afisha == null) {
                return "redirect:/events?error=notfound";
            }
            
            // DEBUG: Логируем информацию об афише и её изображении
            System.out.println("=== DEBUG: WebController afishaDetailPage ===");
            System.out.println("Afisha ID: " + afisha.getId());
            System.out.println("Afisha Name: " + afisha.getName());
            System.out.println("Afisha ImagePath: " + afisha.getImagePath());
            System.out.println("ImagePath is null: " + (afisha.getImagePath() == null));
            System.out.println("ImagePath is empty: " + (afisha.getImagePath() != null && afisha.getImagePath().isEmpty()));
            
            model.addAttribute("afisha", afisha);
            
            // Добавляем информацию о пользователе для отображения формы покупки
            if (authentication != null && authentication.isAuthenticated()) {
                User user = userService.getUserByEmail(authentication.getName());
                System.err.println("=== User details for afisha detail ===");
                System.err.println("User email: " + user.getEmail());
                System.err.println("User cash: " + user.getCash());
                System.err.println("User cash type: " + (user.getCash() != null ? user.getCash().getClass().getSimpleName() : "null"));
                model.addAttribute("user", user);
            }
            
            return "pages/afisha-detail";
        } catch (Exception e) {
            return "redirect:/events?error=notfound";
        }
    }

    @GetMapping("/venues")
    public String placesPage(Model model, 
                            @RequestParam(required = false) String search,
                            @RequestParam(required = false) String city,
                            @RequestParam(required = false) com.example.qydyr.model.enums.Category category) {
        List<Place> places;
        
        // Check if any filter is applied (non-empty search, city, or category)
        boolean hasFilters = (search != null && !search.trim().isEmpty()) || 
                           (city != null && !city.trim().isEmpty()) || 
                           category != null;
        
        if (hasFilters) {
            // Use search and filter functionality
            places = placeService.getPlacesBySearchAndFilter(search, city, category);
        } else {
            // Use default - get all published places
            places = placeService.getAll().stream()
                    .filter(place -> place.getPublished() != null && place.getPublished())
                    .collect(java.util.stream.Collectors.toList());
        }
        
        model.addAttribute("places", places);
        model.addAttribute("search", search);
        model.addAttribute("city", city);
        model.addAttribute("category", category);
        
        return "pages/places";
    }

    @GetMapping("/favourites")
    public String favouritesPage(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        // TODO: Implement favourite service methods
        // List<Afisha> favouriteAfisha = favouriteService.getFavouriteAfisha(currentUser.getId());
        // List<Place> favouritePlaces = favouriteService.getFavouritePlaces(currentUser.getId());
        
        model.addAttribute("favouriteAfisha", new ArrayList<>());
        model.addAttribute("favouritePlaces", new ArrayList<>());
        return "pages/favourites";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("userUpdateRequest", new UserUpdateRequest());
        return "pages/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@Valid @ModelAttribute UserUpdateRequest request,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", currentUser);
            return "pages/profile";
        }

        try {
            // TODO: Implement user update service
            // userService.updateUser(currentUser.getId(), request);
            redirectAttributes.addFlashAttribute("message", "Профиль успешно обновлен!");
            return "redirect:/profile";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка обновления профиля: " + e.getMessage());
            model.addAttribute("user", currentUser);
            return "pages/profile";
        }
    }

    @PostMapping("/profile/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Пароли не совпадают");
            model.addAttribute("user", currentUser);
            return "pages/profile";
        }

        try {
            // TODO: Implement password change service
            // userService.changePassword(currentUser.getId(), currentPassword, newPassword);
            redirectAttributes.addFlashAttribute("message", "Пароль успешно изменен!");
            return "redirect:/profile";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка смены пароля: " + e.getMessage());
            model.addAttribute("user", currentUser);
            return "pages/profile";
        }
    }

    @PostMapping("/profile/notifications")
    public String updateNotifications(@RequestParam(required = false) boolean emailNotifications,
                                     @RequestParam(required = false) boolean newEvents,
                                     @RequestParam(required = false) boolean orderUpdates,
                                     @RequestParam(required = false) boolean marketing,
                                     RedirectAttributes redirectAttributes) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        try {
            // TODO: Implement notification preferences service
            // userService.updateNotificationPreferences(currentUser.getId(), emailNotifications, newEvents, orderUpdates, marketing);
            redirectAttributes.addFlashAttribute("message", "Настройки уведомлений сохранены!");
            return "redirect:/profile";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ошибка сохранения настроек: " + e.getMessage());
            return "redirect:/profile";
        }
    }


    @GetMapping("/events/new")
    public String newAfishaPage(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("user", currentUser);
        return "pages/afisha-form";
    }

    @GetMapping("/venues/new")
    public String newPlacePage(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("user", currentUser);
        return "pages/place-form";
    }


    @PostMapping("/purchase/{afishaId}")
    public String purchaseTicket(@PathVariable Long afishaId, 
                                @RequestParam(defaultValue = "1") Integer quantity,
                                RedirectAttributes redirectAttributes) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "Для покупки билетов необходимо войти в систему");
            return "redirect:/login";
        }

        try {
            // Покупаем билеты одним заказом
            paymentService.buyAfisha(currentUser.getId(), afishaId, quantity);
            
            redirectAttributes.addFlashAttribute("success", 
                "Билеты успешно приобретены! Количество: " + quantity);
            return "redirect:/orders";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Ошибка при покупке билетов: " + e.getMessage());
            return "redirect:/events/" + afishaId;
        }
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        List<Order> orders = orderRepository.findByUserOrderByCreatedAtDesc(currentUser);
        model.addAttribute("orders", orders);
        model.addAttribute("user", currentUser);
        return "pages/orders";
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated() && 
            !authentication.getName().equals("anonymousUser")) {
            try {
                return userService.getUserByEmail(authentication.getName());
            } catch (Exception e) {
                System.err.println("Error getting user by email: " + e.getMessage());
                return null;
            }
        }
        return null;
    }
}
