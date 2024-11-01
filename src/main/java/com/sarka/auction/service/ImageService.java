package com.sarka.auction.service;

import com.sarka.auction.DTO.ImageDTO;
import com.sarka.auction.model.Auction;
import com.sarka.auction.model.Image;
import com.sarka.auction.repository.AuctionRepository;
import com.sarka.auction.repository.ImageRepository;
import com.sarka.auction.service.interfaces.IImageService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final AuctionRepository auctionRepository;

    public ImageService(ImageRepository imageRepository, AuctionRepository auctionRepository) {
        this.imageRepository = imageRepository;
        this.auctionRepository = auctionRepository;
    }

    @Override
    public Image uploadImage(Long auctionId, MultipartFile imageFile) throws IOException {
        //first fetch the auction that we want to upload the image to, find it by its id
        Auction auction = auctionRepository.findById(auctionId).get();
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String extractedUserId = jwt.getClaimAsString("sub");
        if(!auction.getUserId().equals(extractedUserId)){
            throw new IllegalArgumentException("Only owner of the auction can uploade image to it!");
        }
        Image imageToUpload = new Image(
                imageFile.getOriginalFilename(),
                imageFile.getContentType(),
                imageFile.getBytes(), auction);



        return imageRepository.save(imageToUpload);
    }

    @Override
    public ImageDTO getImageByAuctionId(Long auctionId) {
        Image image = imageRepository.findByAuctionId(auctionId);
        System.out.println("auction id we tryna fetch from " + auctionId);
        System.out.println("image name " + image.getName());
        if (image == null){
            throw new IllegalArgumentException("Image not found for auction ID: " + auctionId);
        }
        String base64Image = Base64.getEncoder().encodeToString(image.getImageData());
        return new ImageDTO(image.getName(),image.getType(),base64Image);
    }
}
