package com.sarka.auction.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_name")
    private String name;

    @Column(name = "image_type")
    private String type;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @OneToOne
    @JoinColumn(name = "auction_id") // foreign key
    private Auction auction;

    public Image(String name, String type, byte[] imageData, Auction auction) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.auction = auction;
    }
}
