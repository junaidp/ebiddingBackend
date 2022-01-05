package com.ebidding.model;

import com.google.gson.Gson;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Project extends PanacheMongoEntity {

   // @Id
   // @GeneratedValue private Long id;
    String name;
    String description;
    String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public static Project valueOf(String string)
    {
        Gson gson = new Gson();
        return gson.fromJson(string, Project.class);
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
