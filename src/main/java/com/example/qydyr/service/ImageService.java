package com.example.qydyr.service;

import com.example.qydyr.model.Image;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image uploadImagePlace(MultipartFile file, String name, Long placeId) throws IOException;
    List<Image> uploadMultipleImagesPlace(List<MultipartFile> files, String name, Long placeId) throws IOException;

    Image uploadImageAfisha(MultipartFile file, String name, Long afishaId) throws IOException;
    List<Image> uploadMultipleImagesAfisha(List<MultipartFile> files, String name, Long afishaId) throws IOException;

}
