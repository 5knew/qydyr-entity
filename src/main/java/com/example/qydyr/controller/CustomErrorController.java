package com.example.qydyr.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorTitle", "Страница не найдена");
                model.addAttribute("errorMessage", "Запрашиваемая страница не существует.");
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorTitle", "Внутренняя ошибка сервера");
                model.addAttribute("errorMessage", "Произошла ошибка при обработке запроса.");
                return "error/500";
            }
        }
        
        model.addAttribute("errorTitle", "Ошибка");
        model.addAttribute("errorMessage", "Произошла неизвестная ошибка.");
        return "error/generic";
    }
}
