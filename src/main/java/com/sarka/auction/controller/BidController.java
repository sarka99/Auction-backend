package com.sarka.auction.controller;

import com.sarka.auction.DTO.BidDTO;
import com.sarka.auction.DTO.BidResponseDTO;
import com.sarka.auction.model.Bid;
import com.sarka.auction.service.AuctionService;
import com.sarka.auction.service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService, AuctionService auctionService) {
        this.bidService = bidService;
    }
    @PostMapping("/auctions/{auctionId}/bids")
    public ResponseEntity<BidResponseDTO> placeBid(@PathVariable Long auctionId, @RequestBody BidDTO bidDTO){
        Bid newBid = bidService.placeBid(auctionId,bidDTO.getAmount());
        //return BidResponseDTO instead of entire bid object containing the Auction
        BidResponseDTO bidResponseDTO = new BidResponseDTO(newBid.getId(),newBid.getAmount(),newBid.getTime());
        return ResponseEntity.ok(bidResponseDTO);
    }

}
