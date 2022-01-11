package com.ebidding.dto;

import com.ebidding.model.Company;
import com.ebidding.model.User;

import java.io.Serializable;

public class CompanyDTO implements Serializable {

        Company company;
        User admin;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
