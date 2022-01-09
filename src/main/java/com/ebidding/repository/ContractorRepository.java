package com.ebidding.repository;

import com.ebidding.model.Contractor;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ContractorRepository implements PanacheMongoRepository<Contractor> {

    public List<Contractor> findByCompany(String companyId){

        return find("companyId", companyId).list();
    }



    @Override
    public void persist(Contractor contractor) {
        contractor.setContractorId(UUID.randomUUID().toString());
        contractor.persist();
    }
}
