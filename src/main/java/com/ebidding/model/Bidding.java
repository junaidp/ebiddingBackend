package com.ebidding.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Bidding extends PanacheMongoEntity {

    private String buddingId;
    private String bidId;
    private String contractorId;
    private String amount;

    public String getBuddingId() {
        return buddingId;
    }

    public void setBuddingId(String buddingId) {
        this.buddingId = buddingId;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
