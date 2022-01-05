package com.ebidding.model;

import com.google.gson.Gson;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Company extends PanacheMongoEntity {

    String name;
    String address ;

    public static Company valueOf(String string)
    {
        Gson gson = new Gson();
        return gson.fromJson(string, Company.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
