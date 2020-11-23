package com.example.testspring.model.userModel;

public class UserRole {
    private String roleId;
    private String userId;

    public UserRole() {
    }

    public UserRole(String roleId, String userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
