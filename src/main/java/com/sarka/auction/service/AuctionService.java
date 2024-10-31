package com.sarka.auction.service;

/*
This service class contains the business logic for auction and has all the logic for the CRUD methods
Will use the auction repository to actually interact with the persistence layer
*/

import com.sarka.auction.model.Auction;
import com.sarka.auction.repository.AuctionRepository;
import com.sarka.auction.service.interfaces.IAuctionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuctionService implements IAuctionService {
    private final AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public Auction createAuction(Auction auction) {
        // Get the current JWT token
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String extractedUserId = jwt.getClaimAsString("sub");
        System.out.println("Extracted user id from the person whos trying to create auction is" + extractedUserId);

        // Set the auction owner's ID to the one retrieved from the JWT token for security
        auction.setUserId(extractedUserId);
        if(auction.getName() == null || auction.getName().isEmpty()){
            throw new IllegalArgumentException("Auction name is required");
        }
        if(auction.getDescription() == null || auction.getDescription().isEmpty()){
            throw new IllegalArgumentException("Auction description is required");
        }
        if(auction.getStartingPrice() <= 0){
            throw new IllegalArgumentException("Starting price must be positive");
        }
        if(auction.getEndDateTime() == null){
            throw new IllegalArgumentException("Auction end date and time are required");
        }

        // Validate end date/time to make sure it's after the current time (must be in the future)
        if(auction.getEndDateTime().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Auction end date and time must be in the future");
        }
        // Of all validations pass, then save the auction
        return auctionRepository.save(auction);
    }

    @Override
    public Auction editAuctionDescription(String newDescription, Long auctionId) {
        //get this existing auction so we can update its newDescription
        Optional<Auction> existingAuction = auctionRepository.findById(auctionId);
        if(existingAuction.isEmpty()){
            throw new IllegalArgumentException("Auction not found");
        }

        // Get the current JWT token, and extract userId
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String extractedUserId = jwt.getClaimAsString("sub");

        if(!existingAuction.get().getUserId().equals(extractedUserId)){
            throw new IllegalArgumentException("Unauthorized to edit this auction's description");
        }
        if (newDescription.trim().isEmpty() || newDescription == null){
            throw new IllegalArgumentException("Description cannot be empty or null");
        }

        existingAuction.get().setDescription(newDescription);
        return auctionRepository.save(existingAuction.get());
    }
}
