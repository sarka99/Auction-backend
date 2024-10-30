package com.sarka.auction.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bid")
public class Bid {
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    private Long id;

    @Column(name = "bid_amount", nullable = false)
    private double amount;

    @Column(name = "bid_time", nullable = false)
    private LocalDateTime time;

    @ManyToOne // Bid belongs to an Auction
    @JoinColumn(name = "auction_id", nullable = false) // Foreign key
    private Auction auction;

    @Column(name = "bid_user_id", nullable = false)
    private String userId;


}
