package com.ebidding.model;

import com.google.gson.Gson;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.util.List;

public class Bid extends PanacheMongoEntity {

    private String bidId;

      private String name;
    private String date;
    private String companyId;
    private List<String> contractorId;
    private String projectId;

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public List<String> getContractorId() {
        return contractorId;
    }

    public void setContractorId(List<String> contractorId) {
        this.contractorId = contractorId;
    }

    public static Bid valueOf(String string)
    {
        Gson gson = new Gson();
        return gson.fromJson(string, Bid.class);
    }


}
