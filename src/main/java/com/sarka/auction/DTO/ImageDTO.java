package com.sarka.auction.DTO;

import com.sarka.auction.model.Auction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private String name;
    private String type;
    private String data; // This will hold the Base64 encoded image string
}