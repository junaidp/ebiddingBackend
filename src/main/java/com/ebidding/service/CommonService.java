package com.ebidding.service;

import com.ebidding.model.Company;
import com.ebidding.model.CompanyDTO;
import com.ebidding.model.ErrorInfo;
import com.ebidding.model.User;
import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CommonService {

    @Inject
    CompanyService companyService;
    @Inject
    UserService userService;
    Gson gson = new Gson();

    public String saveCompanyandItsAdmin(CompanyDTO companyDto){

        String companyId = companyService.saveCompany(companyDto.getCompany());
        User admin = companyDto.getAdmin();
        admin.setCompanyId(companyId);
        userService.saveUser(admin);
        ErrorInfo errorInfo = new ErrorInfo(true, "company saved");
        return gson.toJson(errorInfo);

    }
}
