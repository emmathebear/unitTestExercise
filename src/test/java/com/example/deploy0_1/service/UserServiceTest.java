package com.example.deploy0_1.service;

import com.example.deploy0_1.entity.User;
import com.example.deploy0_1.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private static User USER_DEFAULT;
    private static User USER_UPDATED;

    @BeforeEach
    void setUp() {
        USER_DEFAULT = new User(1L,"username","password");
        USER_UPDATED = new User(1L,"username","password");
    }

    @Test
    void save() {
        when(userRepository.save(any(User.class)))
                .thenReturn(USER_DEFAULT);
        User userReturned = userService.save( new User(0L,"",""));
        assertEquals(USER_DEFAULT, userReturned);

    }

    @Test
    void findByUsername() {
        when(userRepository.findByUsername("username"))
                .thenReturn(USER_DEFAULT);
        User userReturned = userService.findByUsername("username");
        assertEquals(USER_DEFAULT, userReturned);
    }

    @Test
    void updateUserPassword() {
        when(userRepository.findByUsername(any(String.class)))
                .thenReturn(USER_DEFAULT);
        when(userRepository.save(any(User.class)))
                .thenReturn(USER_UPDATED);

        User userReturned = userService.updateUserPassword("username", "updatedPassword");
        assertEquals(USER_UPDATED, userReturned);
    }

}