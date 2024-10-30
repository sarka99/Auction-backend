package com.sarka.auction.controller;

import com.sarka.auction.DTO.EditAuctionDescriptionDTO;
import com.sarka.auction.model.Auction;
import com.sarka.auction.service.AuctionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
