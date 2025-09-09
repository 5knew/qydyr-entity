package com.example.qydyr.service.impl;

import com.example.qydyr.exception.AfishaNotAvailableException;
import com.example.qydyr.exception.AfishaNotFoundException;
import com.example.qydyr.exception.InsufficientFundsException;
import com.example.qydyr.model.Afisha;
import com.example.qydyr.model.Order;
import com.example.qydyr.model.User;
import com.example.qydyr.model.enums.Status;
import com.example.qydyr.repository.AfishaRepository;
import com.example.qydyr.repository.OrderRepository;
import com.example.qydyr.service.EmailSender;
import com.example.qydyr.service.PaymentService;
import com.example.qydyr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final AfishaRepository afishaRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final EmailSender emailSender;

    public void buyAfisha(Long userId, Long afishaId) {
        buyAfisha(userId, afishaId, 1);
    }

    public void buyAfisha(Long userId, Long afishaId, Integer quantity) {
        User user = userService.getUserById(userId);
        Afisha afisha = afishaRepository.findById(afishaId)
                .orElseThrow(() -> {
                    emailSender.send(user.getEmail(), "Afisha not found with ID: " + afishaId);
                    return new AfishaNotFoundException("Afisha not found with ID: " + afishaId);
                });
        
        if (afisha.getStatus() != Status.ACTIVE) {
            emailSender.send(user.getEmail(), "Afisha is not available for purchase.");
            throw new AfishaNotAvailableException("Afisha is not available for purchase.");
        }

        double cash = Double.parseDouble(user.getCash());
        double price = afisha.getPrice();
        double totalCost = price * quantity;
        
        if (cash < totalCost) {
            emailSender.send(user.getEmail(), "User does not have enough cash to buy the Afisha.");
            throw new InsufficientFundsException("User does not have enough cash to buy the Afisha. Required: " + totalCost + ", Available: " + cash);
        }

        double newCash = cash - totalCost;
        user.setCash(String.valueOf(newCash));
        userService.updateUser(user);

        Order order = new Order();
        order.setUser(user);
        order.setAfisha(afisha);
        order.setStatus(Status.PURCHASED);
        order.setCount(quantity);
        orderRepository.save(order);
        
        emailSender.send(user.getEmail(), 
            "Уважаемый " + user.getFirstName() + "! Билеты успешно приобретены на посещение " + afisha.getName() + 
            ". Количество: " + quantity + ", Общая стоимость: " + totalCost + " ₸");
    }
}

