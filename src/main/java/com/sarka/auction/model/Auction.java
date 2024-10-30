package com.sarka.auction.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auction")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private Long id;

    @Column(name = "auction_name", nullable = false)
    private String name;

    @Column(name = "auction_description", nullable = false)
    private String description;

    @Column(name = "auction_starting_price", nullable = false)
    private double startingPrice;

    @Column(name = "auction_image_data", nullable = true)
    private String imageData;

    @Column(name = "auction_end_date_time", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "auction_user_id", nullable = false)
    private String userId;

    // The cascadeType.all setting means that any operation performed on auction will also affect its bids
    //For instance, if an auction is deleted all associated bids will also be deleted
    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids;


    public Auction(Long id, String name, String description, double startingPrice, String imageData, LocalDateTime endDateTime, String userId) {
    }
}
