package com.sarka.auction.repository;

import com.sarka.auction.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction,Long> {

    @Query(value = "SELECT * FROM auction WHERE auction_end_date_time > :currentDateTime ORDER BY auction_end_date_time ASC", nativeQuery = true)
    List<Auction> findAllActiveAuctions(LocalDateTime currentDateTime);




}
