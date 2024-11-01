package com.sarka.auction.service.interfaces;
import com.sarka.auction.DTO.ImageDTO;
import com.sarka.auction.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {
    public Image uploadImage(Long auctionId, MultipartFile imageFile) throws IOException;
    public ImageDTO getImageByAuctionId(Long auctionId);

}
