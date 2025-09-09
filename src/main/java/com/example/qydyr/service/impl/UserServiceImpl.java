package com.example.qydyr.service.impl;

import com.example.qydyr.exception.UserNotFoundException;
import com.example.qydyr.model.User;
import com.example.qydyr.repository.UserRepository;
import com.example.qydyr.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void depositCash(Long userId, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        double currentCash = Double.parseDouble(user.getCash());
        double newCash = currentCash + amount;
        user.setCash(String.valueOf(newCash));

        // Сохраняем только обновленное поле cash, не трогая пароль
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public void updateUser(User user) {
        // Получаем существующего пользователя из базы данных
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + user.getId()));
        
        // Обновляем только не-null поля, сохраняя пароль
        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getCash() != null) {
            existingUser.setCash(user.getCash());
        }
        if (user.getRole() != null) {
            existingUser.setRole(user.getRole());
        }
        // Пароль обновляем только если он явно указан и не пустой
        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            existingUser.setPassword(user.getPassword());
        }
        
        userRepository.save(existingUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public Page<User> getAllUsers(org.springframework.data.domain.Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}