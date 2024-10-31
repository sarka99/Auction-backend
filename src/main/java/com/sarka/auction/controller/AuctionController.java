package com.sarka.auction.controller;

import com.sarka.auction.DTO.EditAuctionDescriptionDTO;
import com.sarka.auction.model.Auction;
import com.sarka.auction.service.AuctionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }


    @PostMapping("/auctions/create")
    public ResponseEntity<Auction> createAuction(@RequestBody Auction auction) {
        //auction parameter comes from the RequestBody sent from the frontend service
        return ResponseEntity.ok(auctionService.createAuction(auction));
    }
    @PostMapping("/auctions/{auctionId}/description")
    public ResponseEntity<Auction> editAuctionDescription(@PathVariable Long auctionId, @RequestBody EditAuctionDescriptionDTO dto) {
        Auction updatedAuction = auctionService.editAuctionDescription(dto.getNewDescription(), auctionId);
        return ResponseEntity.ok(updatedAuction);
    }

    @GetMapping("/auctions/active")
    //TODO: Test this endpoint later, make sure it only returns the active auctions
    public ResponseEntity<List<Auction>> getAllActiveAuctions(){
        List<Auction> activeAuctions = auctionService.getAllActiveAuctions();
        return ResponseEntity.ok(activeAuctions);
    }
    @GetMapping("/auctions/{auctionId}")
    public ResponseEntity<Auction> getAuctionDetails(@PathVariable Long auctionId){
        Auction auction = auctionService.getAuctionDetails(auctionId);
        return ResponseEntity.ok(auction);
    }




}
