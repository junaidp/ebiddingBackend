package com.ebidding.model;

import com.google.gson.Gson;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Contractor extends PanacheMongoEntity {

    String name;
    String description;
    String email;
    String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Contractor valueOf(String string)
    {
        Gson gson = new Gson();
        return gson.fromJson(string, Contractor.class);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
