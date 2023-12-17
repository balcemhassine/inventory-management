package com.tbs.inventorymanagement.service;


import com.tbs.inventorymanagement.entity.UserEntity;
import com.tbs.inventorymanagement.mapper.UserMapper;
import com.tbs.inventorymanagement.model.Product;
import com.tbs.inventorymanagement.model.User;
import com.tbs.inventorymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service

public class UserManager {

    // private static List<User> users = new ArrayList<>() ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public List<User> getAll(){
        return userMapper.entityToModel(userRepository.findAll());
    }


    public User getUser(int userID){
        User userResult = userMapper.entityToModel(userRepository.findUserByUserId(userID));
        if (userResult == null){
            throw new NoSuchElementException(String.format("The id %d is not found", userID));
        }
        return userResult;
    }
    public List<User> addUsers (List<User> users){
        return userMapper.entityToModel(userRepository.saveAll(userMapper.modelToEntity(users)));
    }
    public User addUser(User user){
        UserEntity userToBeSaved = userMapper.modelToEntity(user);
        UserEntity save= userRepository.save(userToBeSaved);
        System.out.println(String.format("The user %s is added successfully",userToBeSaved));
        return userMapper.entityToModel(save);
    }

    public List<User> updateUser(int userToBeUpdated,User user){
        return null;
    }

    public boolean removeUser(int idUserToBeRemoved){
        userRepository.deleteById(idUserToBeRemoved);
        return true;
    }

    public User getUserById(int userId) {

        return userMapper.entityToModel(userRepository.findUserByUserId(userId));
    }
}

