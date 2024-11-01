package com.sarka.auction.repository;

import com.sarka.auction.model.Auction;
import com.sarka.auction.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long> {
    //@Query(value = "SELECT * FROM image WHERE auction_id = :auctionId", nativeQuery = true)
    Image findByAuctionId(Long auctionId);
}
