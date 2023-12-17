package com.tbs.inventorymanagement.entity;



import com.tbs.inventorymanagement.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private Integer userId;
    private String userName;
    private String password;
    private String role;
    private boolean isLoggedIn;





}
