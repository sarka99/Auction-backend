package com.sarka.auction.service;

/*
This service class contains the business logic for auction and has all the logic for the CRUD methods
Will use the auction repository to actually interact with the persistence layer
*/

import com.sarka.auction.model.Auction;
import com.sarka.auction.repository.AuctionRepository;
import com.sarka.auction.service.interfaces.IAuctionService;
import org.springframework.stereotype.Service;

@Service
public class AuctionService implements IAuctionService {
    private final AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public Auction createAuction(Auction auction) {
        return null;
    }
}
