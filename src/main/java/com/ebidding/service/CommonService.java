package com.ebidding.service;

import com.ebidding.dto.CompanyDTO;
import com.ebidding.dto.Role;
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
        admin.setRole(Role.ADMIN);
        admin.setCompanyId(companyId);
        userService.saveUser(admin);
        ErrorInfo errorInfo = new ErrorInfo(true, "company saved");
        return gson.toJson(errorInfo);

    }
}
