package com.ebidding.service;

import com.ebidding.dto.Role;
import com.ebidding.model.*;
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
            List<User> users =  User.find("companyId", bid.getCompanyId()).list();
            for(User user : users){
             if(user.getRole() == Role.ADMIN)
                sendEmail(user.getEmail());
            }
          
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

    private void sendEmail(String email) {
    }

    public String getBids(String companyId){
        List<Bid> bids = bidRepository.findByCompany(companyId);
        return gson.toJson(bids);
    }

    public String saveBidding(Bidding bidding){
        try{
            bidding.persist();
            ErrorInfo response = new ErrorInfo(true, "bidding saved");
            return gson.toJson(response);
        }catch(Exception ex){
            ErrorInfo response = new ErrorInfo(false, "bidding could not saved" + ex);
            return gson.toJson(response);
        }

    }

    public String getBiddings(String bidId) {
        try{
            List<Bidding> biddings = Bidding.find("bidId", bidId ).list();
            return gson.toJson(biddings);
        }catch(Exception ex){
            ErrorInfo response = new ErrorInfo(false, "failed to fetch biddings" + ex);
            return gson.toJson(response);
        }
    }
}
