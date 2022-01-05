package com.ebidding.repository;

import com.ebidding.model.Contractor;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ContractorRepository implements PanacheMongoRepository<Contractor> {

    public List<Contractor> findByCompany(String companyId){

        return find("companyId", companyId).list();
    }



    @Override
    public void persist(Contractor contractor) {
        contractor.persist();
    }
}
