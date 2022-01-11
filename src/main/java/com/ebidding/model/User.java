package com.ebidding.model;

import com.ebidding.dto.Role;
import com.google.gson.Gson;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class User extends PanacheMongoEntity {

    String name;
    String companyId;
    String password;
    String email;
    private Role role;
    private String userId;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static User valueOf(String string)
    {
        Gson gson = new Gson();
        return gson.fromJson(string, User.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
