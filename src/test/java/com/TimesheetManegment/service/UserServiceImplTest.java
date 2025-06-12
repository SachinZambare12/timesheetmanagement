package com.TimesheetManegment.service;



import com.TimesheetManegment.Entity.User;
import com.TimesheetManegment.Repository.UserRepository;
import com.TimesheetManegment.Service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Arrange
        User inputUser = new User();
        inputUser.setUsername("testuser");
        inputUser.setPassword("plainPassword");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");
        savedUser.setPassword("encodedPassword");

        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = userService.register(inputUser);

        // Assert
        assertEquals("testuser", result.getUsername());
        assertEquals("encodedPassword", result.getPassword());
        verify(passwordEncoder).encode("plainPassword");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testGetAllUsers() {

                User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setPassword("pass1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");
        user2.setPassword("pass2");
        List <User> mockUsers = List.of(user1 ,user2);




        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById_Success() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john");
        user.setPassword("pass");
        user.setRole(null);  // if role field exists

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals("john", result.getUsername());
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
        assertEquals("User not found", ex.getMessage());

        verify(userRepository).findById(1L);
    }
}

