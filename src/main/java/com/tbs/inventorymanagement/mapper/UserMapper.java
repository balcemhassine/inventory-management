package com.tbs.inventorymanagement.mapper;

import com.tbs.inventorymanagement.entity.ProductEntity;
import com.tbs.inventorymanagement.entity.UserEntity;
import com.tbs.inventorymanagement.model.Product;
import com.tbs.inventorymanagement.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserEntity modelToEntity(User u);
    User entityToModel(UserEntity e);
    List<User> entityToModel(List<UserEntity> e);

    List<UserEntity> modelToEntity (List<User> a);
}
