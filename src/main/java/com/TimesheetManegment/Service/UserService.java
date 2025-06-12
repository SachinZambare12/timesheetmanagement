package com.TimesheetManegment.Service;



import com.TimesheetManegment.Entity.User;

import java.util.List;

public interface UserService {
   public User register(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
}

