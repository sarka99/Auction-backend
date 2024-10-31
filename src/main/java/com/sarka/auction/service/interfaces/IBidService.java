package com.sarka.auction.service.interfaces;

import com.sarka.auction.model.Bid;

public interface IBidService {
    Bid placeBid(Long auctionId, double amount);
}
