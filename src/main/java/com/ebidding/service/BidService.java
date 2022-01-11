package com.ebidding.service;

import com.ebidding.dto.Role;
import com.ebidding.model.*;
import com.ebidding.repository.BidRepository;
import com.ebidding.util.Utility;
import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

@ApplicationScoped
public class BidService {

    @Inject
    BidRepository bidRepository;
    @Inject
    Utility utility;

    Gson gson = new Gson();

    public String saveBid(Bid bid) {
        ErrorInfo response = new ErrorInfo();
        try {
            bid.setBidId(UUID.randomUUID().toString());
            System.out.println("Saving bid");
            bid.persist();
          //  List<Contractor> users =  Contractor.find("contractorId", bid.getContractorId().get(0)).list();
           //TODO: DO THIS WITH QUARKUS QUERY
            List<Contractor> contractors = new ArrayList<Contractor>();
            List<Contractor> allContractors =  Contractor.findAll().list();
            for(Contractor contractor : allContractors){
                if(bid.getContractorId().contains(contractor.getContractorId()))
                  contractors.add(contractor);
            }
            for(Contractor user : contractors){
           //  if(user.getRole() == Role.ADMIN)
                sendEmail(user.getEmail(), bid.getBidId(), user.getContractorId());
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

    private void sendEmail(String email, String bidId, String contractorId) {
            utility.sendEmail(email, "Bidding", "https://ebidding.herokuapp.com/bidding?bid:"+bidId+"&contractor:"+contractorId);

    }

    public String getBids(String companyId){
        List<Bid> bids = bidRepository.findByCompany(companyId);
        return gson.toJson(bids);
    }

    public String saveBidding(Bidding bidding){
        try{
            bidding.setBiddingId(UUID.randomUUID().toString());
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

    public String getBiddingResults(String bidId, String contractorId) {
        try {
            List<Bidding> biddings = Bidding.find("bidId", bidId).list();
            String amount = "";
            int lowerBids = 0;
            for (Bidding bidding : biddings) {
                if (bidding.getContractorId().equals(contractorId)) {
                    amount = bidding.getAmount();
                    break;
                }
            }
            for (Bidding bidding : biddings) {
                if (Long.parseLong(bidding.getAmount()) < Long.parseLong(amount)) {
                    lowerBids++;
                }
            }

            ErrorInfo errorInfo;
            if (lowerBids > 0) {
                errorInfo = new ErrorInfo(true, "There are " + lowerBids + " bids lower than yours");
            } else {
                errorInfo = new ErrorInfo(true, "You have the lowest bid");
            }
            return gson.toJson(errorInfo);
        }
        catch(Exception ex ){
            return gson.toJson(new ErrorInfo(false, "Error getting bidding update: "+ ex.getMessage()));
        }

    }

    public String getBid(String bidId) {
        try{
           Bid bid = Bid.find("bidId", bidId).firstResult();
           return gson.toJson(bid);
        }catch(Exception ex){
            ErrorInfo errorInfo = new ErrorInfo(false, "error getting bid: " + ex);
            return gson.toJson(errorInfo);
        }
    }
}
