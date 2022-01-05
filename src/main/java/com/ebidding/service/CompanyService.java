package com.ebidding.service;

import com.ebidding.model.Company;
import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CompanyService {

    Gson gson = new Gson();

    public String saveCompany(Company company){
        try {
            company.persist();
            return gson.toJson("company saved");
        }catch(Exception ex){
            return gson.toJson("Error in saving comapny:" + ex);
        }


    }
    public String getCompanies(){
        List<Company> companies = Company.findAll().list();
        return gson.toJson(companies);

    }

}
