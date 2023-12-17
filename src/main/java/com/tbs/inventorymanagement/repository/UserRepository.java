package com.tbs.inventorymanagement.repository;

import com.tbs.inventorymanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public UserEntity findUserByUserId(Integer id);
}
