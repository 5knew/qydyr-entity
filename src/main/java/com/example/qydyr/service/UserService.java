package com.example.qydyr.service;

import com.example.qydyr.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    void depositCash(Long userId, double amount);

    void updateUser(User user);

    User getUserById(Long userId);
    
    User getUserByEmail(String email);
    
    Page<User> getAllUsers(Pageable pageable);
    long count();
    void deleteUser(Long id);
}

