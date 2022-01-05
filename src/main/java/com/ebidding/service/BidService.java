package com.ebidding.service;

import com.ebidding.model.Bid;
import com.ebidding.repository.BidRepository;
import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BidService {

    @Inject
    BidRepository bidRepository;
    Gson gson = new Gson();

    public String saveBid(Bid bid) {
        String response ;
        try {
            bid.persist();
            response = gson.toJson("Bid Saved");
        }
        catch(Exception ex){
            response = gson.toJson("Bid did not saved: "+ ex.getMessage());
        }
        return response;
    }

    public String getBids(String companyId){
        List<Bid> bids = bidRepository.findByCompany(companyId);
        return gson.toJson(bids);
    }

}
