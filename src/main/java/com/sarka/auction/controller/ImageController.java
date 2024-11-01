package com.sarka.auction.controller;

import com.sarka.auction.DTO.ImageDTO;
import com.sarka.auction.model.Image;
import com.sarka.auction.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    @PostMapping("/auction/{auctionId}/image")
    public ResponseEntity<String> uploadImage(@PathVariable Long auctionId, @RequestParam("file") MultipartFile file) {
        try {
            imageService.uploadImage(auctionId, file);
            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload image: " + e.getMessage());
        }
    }
    @GetMapping("/auction/{auctionId}/image")
    public ResponseEntity<ImageDTO> getImageByAuctionId(@PathVariable Long auctionId){
        ImageDTO imageDTO = imageService.getImageByAuctionId(auctionId);
        return ResponseEntity.ok(imageDTO);
    }
}
