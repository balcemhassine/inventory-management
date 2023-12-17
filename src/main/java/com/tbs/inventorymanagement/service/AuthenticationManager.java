package com.tbs.inventorymanagement.service;

import com.tbs.inventorymanagement.entity.UserEntity;
import com.tbs.inventorymanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthenticationManager {
    private static List<User> users = new ArrayList<>();
    @Autowired
    private UserManager userManager;


    // login method
    public boolean LogIn(int userId, String password) {
        User user = userManager.getUserById(userId);
        if (user != null && user.getPassword().equals(password)) {
            user.setLoggedIn(true);
            return true;
        }
        return false;
    }

    // logOut method
    public boolean LogOut(int userId) {
        User user = userManager.getUserById(userId);
        if (user == null) {
            throw new NoSuchElementException(String.format("The id %d is not found", userId));
        } else {
            user.setLoggedIn(false);
            return true;
        }
    }
}
