package com.ebidding.service;

import com.ebidding.model.Company;
import com.ebidding.model.CompanyDTO;
import com.ebidding.model.ErrorInfo;
import com.google.gson.Gson;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CompanyService {

    Gson gson = new Gson();

    public String saveCompany(Company company){
        try {
            company.setCompanyId(UUID.randomUUID().toString());
            company.persist();
            return company.getCompanyId();
        }catch(Exception ex){
            return gson.toJson("Error in saving company:" + ex);
        }

    }
    public String getCompanies(){
        List<Company> companies = Company.findAll().list();
        return gson.toJson(companies);

    }

}
