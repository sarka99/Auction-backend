package com.sarka.auction.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "auction_end_date_time", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "auction_user_id", nullable = false)
    private String userId;

    @OneToOne(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")  // Foreign key in Auction table
    private Image auctionImage;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Bid> bids = new ArrayList<>();



    public Auction(Long id, String name, String description, double startingPrice, String imageData, LocalDateTime endDateTime, String userId) {
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startingPrice=" + startingPrice +
                ", endDateTime=" + endDateTime +
                ", userId='" + userId + '\'' +
                '}';
    }
}
