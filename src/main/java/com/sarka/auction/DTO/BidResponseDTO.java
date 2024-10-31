package com.sarka.auction.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BidResponseDTO {
    private Long id;
    private double amount;
    private LocalDateTime time;
}
