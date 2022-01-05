package com.ebidding.model;

import com.google.gson.Gson;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Bid extends PanacheMongoEntity {

    public String name;
    public String date;
    public String companyId;


    public static Bid valueOf(String string)
    {
        Gson gson = new Gson();
        return gson.fromJson(string, Bid.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
