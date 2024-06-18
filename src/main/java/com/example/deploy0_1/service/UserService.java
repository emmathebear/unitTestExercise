package com.example.deploy0_1.service;

import com.example.deploy0_1.entity.User;
import com.example.deploy0_1.repository.UserRepository;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUserPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");
        user.setPassword(password);
        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        userRepository.delete(findByUsername(username));
    }
}
