package com.sarka.auction.controller;

import com.sarka.auction.dto.AuctionDTO;
import com.sarka.auction.model.Auction;
import com.sarka.auction.service.AuctionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }


    @PostMapping
    public ResponseEntity<Auction> createAuction(@RequestBody Auction auction) {
        //auction parameter comes from the RequestBody sent from the frontend service
        return ResponseEntity.ok(auctionService.createAuction(auction));
    }



}
