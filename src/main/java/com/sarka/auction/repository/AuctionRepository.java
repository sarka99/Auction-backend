package com.sarka.auction.repository;

import com.sarka.auction.model.Auction;
import com.sarka.auction.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction,Long> {

    @Query(value = "SELECT * FROM auction WHERE auction_end_date_time > :currentDateTime ORDER BY auction_end_date_time ASC", nativeQuery = true)
    List<Auction> findAllActiveAuctions(LocalDateTime currentDateTime);

    @Query(value = "SELECT * FROM auction WHERE auction_end_date_time > :currentTime AND auction_user_id = :userId", nativeQuery = true)
    List<Auction> findAllActiveBiddedAuctions(String userId, LocalDateTime currentTime);


    @Query(value = """
        SELECT a FROM Auction a
        JOIN Bid b ON a.id = b.auction.id
        WHERE a.endDateTime < :currentTime
          AND b.userId = :userId
          AND b.amount = (
              SELECT MAX(b2.amount)
              FROM Bid b2
              WHERE b2.auction.id = a.id
          )
        """)
    List<Auction> findAllWinningbids(String userId, LocalDateTime currentTime);






}
