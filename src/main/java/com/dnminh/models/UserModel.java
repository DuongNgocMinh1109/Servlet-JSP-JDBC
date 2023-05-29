package com.dnminh.models;

public class UserModel extends AbstractModel<UserModel> {
    private String username;
    private String fullName;
    private String password;
    private int status;
    private Long roleId;
    private RoleModel role = new RoleModel();

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public RoleModel getRole() {
        return role;
    }
    public void setRole(RoleModel role) {
        this.role = role;
    }
}
