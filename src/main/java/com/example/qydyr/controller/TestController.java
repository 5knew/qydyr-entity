package com.example.qydyr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TestController {

    @GetMapping("/test-purchase")
    public String testPurchasePage(Model model) {
        model.addAttribute("eventId", 2L);
        model.addAttribute("eventName", "Тестовое мероприятие");
        model.addAttribute("price", 1000.0);
        model.addAttribute("userBalance", 5000.0);
        return "test-purchase";
    }

    @PostMapping("/test-purchase")
    public String testPurchase(@RequestParam Long eventId,
                              @RequestParam Integer quantity,
                              RedirectAttributes redirectAttributes) {
        try {
            // Симуляция покупки
            double price = 1000.0;
            double totalCost = price * quantity;
            double userBalance = 5000.0;
            
            if (userBalance < totalCost) {
                redirectAttributes.addFlashAttribute("error", 
                    "Недостаточно средств. Требуется: " + totalCost + " ₸, доступно: " + userBalance + " ₸");
                return "redirect:/test-purchase";
            }
            
            redirectAttributes.addFlashAttribute("success", 
                "Билеты успешно приобретены! Количество: " + quantity + ", Общая стоимость: " + totalCost + " ₸");
            return "redirect:/test-purchase";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Ошибка при покупке билетов: " + e.getMessage());
            return "redirect:/test-purchase";
        }
    }
}
