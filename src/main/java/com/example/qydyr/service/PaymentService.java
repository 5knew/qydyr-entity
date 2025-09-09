package com.example.qydyr.service;

public interface PaymentService {
    void buyAfisha(Long userId, Long afishaId);
    void buyAfisha(Long userId, Long afishaId, Integer quantity);
}


