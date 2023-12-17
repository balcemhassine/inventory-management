package com.tbs.inventorymanagement.model;


public class User {
    private int userId;
    private String userName;
    private String password;
    private Role role;
    private boolean isLoggedIn;


    public User(int userId, String userName, String password, Role role, boolean isLoggedIn) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.isLoggedIn = isLoggedIn;
    }

    public User() {
    }

    public int getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

}
