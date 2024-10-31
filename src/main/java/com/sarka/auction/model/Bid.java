package com.sarka.auction.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    @JoinColumn(name = "auction_id", nullable = false) // Foreign key
    private Auction auction;

    @Column(name = "bid_user_id", nullable = false)
    private String userId;

    public Bid(double amount, LocalDateTime time, Auction auction, String userId) {
        this.amount = amount;
        this.time = time;
        this.auction = auction;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", amount=" + amount +
                ", time=" + time +
                ", auction=" + auction +
                ", userId='" + userId + '\'' +
                '}';
    }
}
