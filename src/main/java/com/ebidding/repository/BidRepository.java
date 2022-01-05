package com.ebidding.repository;

import com.ebidding.model.Bid;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class BidRepository implements PanacheMongoRepository<Bid> {

    public List<Bid> findByCompany(String companyId){
        return find("companyId", companyId).list();
    }
}
