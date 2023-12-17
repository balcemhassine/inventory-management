package com.tbs.inventorymanagement.controler;

import com.tbs.inventorymanagement.entity.UserEntity;
import com.tbs.inventorymanagement.model.Product;
import com.tbs.inventorymanagement.model.User;
import com.tbs.inventorymanagement.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@Controller
public class UserController {
    @Autowired
    private UserManager userManager;
    private User user ;

    @GetMapping("/")
    public @ResponseBody List<User> getUser() {
        return userManager.getAll();
    }

    @PostMapping("/list")
    public @ResponseBody List<User> addUsers(@RequestBody List<User> newUser){
        return userManager.addUsers(newUser);
    }

    @PostMapping("/")
    public @ResponseBody User addUser(@RequestBody User newUser){
        return userManager.addUser(newUser);
    }

    @DeleteMapping("/")
    public @ResponseBody boolean deleteUser(int idUserToBeDeleted){
        return userManager.removeUser(idUserToBeDeleted);
    }

    @GetMapping("/{userId}")
    public @ResponseBody User getUsernameById(@PathVariable int userId) {
        return userManager.getUserById(userId);
    }
}