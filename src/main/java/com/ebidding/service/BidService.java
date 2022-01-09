package com.ebidding.service;

import com.ebidding.model.Bid;
import com.ebidding.model.ErrorInfo;
import com.ebidding.repository.BidRepository;
import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BidService {

    @Inject
    BidRepository bidRepository;
    Gson gson = new Gson();

    public String saveBid(Bid bid) {
        ErrorInfo response = new ErrorInfo();
        try {
            bid.setBidId(UUID.randomUUID().toString());
            bid.persist();
            response.setSuccess(true);
            response.setMessage("Bid Saved");
            return gson.toJson(response);
        }
        catch(Exception ex){
            response.setSuccess(false);
            response.setMessage("Bid did not saved: "+ ex.getMessage());
            return gson.toJson(response);

        }

    }

    public String getBids(String companyId){
        List<Bid> bids = bidRepository.findByCompany(companyId);
        return gson.toJson(bids);
    }

}
