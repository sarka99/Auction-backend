package com.sarka.auction.service.interfaces;

import com.sarka.auction.model.Auction;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface IAuctionService {
    Auction createAuction(Auction auction);
    Auction editAuctionDescription(String description, Long auctionId);
    List<Auction> getAllActiveAuctions();
    Auction getAuctionDetails(Long auctionId);
    List<Auction> getActiveBiddedAuctions();
}
