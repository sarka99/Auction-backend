package com.sarka.auction.repository;

import com.sarka.auction.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction,Long> {

}
