package com.tbs.inventorymanagement.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserConnectionDto {

    private int userId;
    private String password;

}
