package com.TimesheetManegment.Controller;



import com.TimesheetManegment.Entity.User;
import com.TimesheetManegment.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("john");
        user.setPassword("pass123");

        when(userService.register(any(User.class))).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.register(user);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("john", response.getBody().getUsername());
        verify(userService, times(1)).register(user);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("john");
        user1.setPassword("pass");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("jane");
        user2.setPassword("pass2");

        List<User> userList = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(userList);

        // Act
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Assert
        assertEquals(2, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
        verify(userService, times(1)).getAllUsers();
    }
    @Test
    void testGetUserById() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("john");
        user.setPassword("pass");

        when(userService.getUserById(1L)).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.getUserById(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("john", response.getBody().getUsername());
        verify(userService, times(1)).getUserById(1L);
    }
}

