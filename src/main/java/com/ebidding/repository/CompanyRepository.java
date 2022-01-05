package com.ebidding.repository;

import com.ebidding.model.Company;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompanyRepository implements PanacheMongoRepository<Company> {

}
