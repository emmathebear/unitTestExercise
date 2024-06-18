package com.example.deploy0_1.controller;

import com.example.deploy0_1.entity.User;
import com.example.deploy0_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerUser {
    @Autowired
    private UserService userService;

    @PutMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(
                userService.save(user));
    }

    @PatchMapping(path = "{username}")
    public ResponseEntity<User> updateUserPassword(@PathVariable String username, @RequestParam String newPassword) {
        return ResponseEntity.ok(
                userService.updateUserPassword(username, newPassword));
    }

    @GetMapping(path = "{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(
                userService.findByUsername(username));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
