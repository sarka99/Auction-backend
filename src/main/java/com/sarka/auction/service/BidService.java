package com.sarka.auction.service;

import com.sarka.auction.model.Auction;
import com.sarka.auction.model.Bid;
import com.sarka.auction.repository.AuctionRepository;
import com.sarka.auction.repository.BidRepository;
import com.sarka.auction.service.interfaces.IBidService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BidService implements IBidService {
    private final BidRepository bidRepository;
    private final AuctionRepository auctionRepository;

    public BidService(BidRepository bidRepository, AuctionRepository auctionRepository) {
        this.bidRepository = bidRepository;
        this.auctionRepository = auctionRepository;
    }


    @Override
    @Transactional
    public Bid placeBid(Long auctionId, double amount) {
        // Get the current JWT token, and extract userId
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String extractedUserId = jwt.getClaimAsString("sub");



        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new IllegalArgumentException("Auction not found"));

        //Bid placement can only be done on an auction that isnt owned by the user trying to place the bid
        if (auction.getUserId().equals(extractedUserId)){
            throw new IllegalArgumentException("The owner of auction cannot place bid");
        }

        // Create a new bid without adding it to auction's bids list
        Bid newBidToPlace = new Bid(amount, LocalDateTime.now(), auction, extractedUserId);
        System.out.println("The new bid we are about to place: " + newBidToPlace);
        System.out.println("the auction we are placing bid on: " + auction);
        // Save the new bid directly
        return bidRepository.save(newBidToPlace);
    }

}
