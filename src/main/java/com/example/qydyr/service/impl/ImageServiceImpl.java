package com.example.qydyr.service.impl;

import com.example.qydyr.model.Afisha;
import com.example.qydyr.model.Image;
import com.example.qydyr.model.Place;
import com.example.qydyr.repository.AfishaRepository;
import com.example.qydyr.repository.ImageRepository;
import com.example.qydyr.repository.PlaceRepository;
import com.example.qydyr.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final PlaceRepository placeRepository;
    private final AfishaRepository afishaRepository;

    public Image uploadImagePlace(MultipartFile file, String name, Long placeId) throws IOException {
        byte[] bytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        long size = file.getSize();
        boolean previewImage = false;

        Optional<Place> optionalPlace = placeRepository.findById(placeId);
        if (optionalPlace.isEmpty()) {
            throw new IllegalArgumentException("Place not found with ID: " + placeId);
        }

        Place place = optionalPlace.get();

        Image image = new Image();
        image.setName(name);
        image.setOriginalFileName(originalFileName);
        image.setSize(size);
        image.setContentType(contentType);
        image.setPreviewImage(previewImage);
        image.setBytes(bytes);
        image.setPlace(place);

        return imageRepository.save(image);
    }

    public List<Image> uploadMultipleImagesPlace(List<MultipartFile> files, String name, Long placeId) throws IOException {
        Optional<Place> optionalPlace = placeRepository.findById(placeId);
        if (optionalPlace.isEmpty()) {
            throw new IllegalArgumentException("Place not found with ID: " + placeId);
        }

        Place place = optionalPlace.get();
        List<Image> uploadedImages = new ArrayList<>();

        for (MultipartFile file : files) {
            Image uploadedImage = uploadImagePlace(file, name, place.getId());
            uploadedImages.add(uploadedImage);
        }

        return uploadedImages;
    }

    public Image uploadImageAfisha(MultipartFile file, String name, Long afishaId) throws IOException {
        byte[] bytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        long size = file.getSize();
        boolean previewImage = false;

        Optional<Afisha> optionalAfisha = afishaRepository.findById(afishaId);
        if (optionalAfisha.isEmpty()) {
            throw new IllegalArgumentException("Afisha not found with ID: " + afishaId);
        }

        Afisha afisha = optionalAfisha.get();

        Image image = new Image();
        image.setName(name);
        image.setOriginalFileName(originalFileName);
        image.setSize(size);
        image.setContentType(contentType);
        image.setPreviewImage(previewImage);
        image.setBytes(bytes);
        image.setAfisha(afisha);

        return imageRepository.save(image);
    }

    public List<Image> uploadMultipleImagesAfisha(List<MultipartFile> files, String name, Long afishaId) throws IOException {
        Optional<Afisha> optionalAfisha = afishaRepository.findById(afishaId);
        if (optionalAfisha.isEmpty()) {
            throw new IllegalArgumentException("Afisha not found with ID: " + afishaId);
        }

        Afisha afisha = optionalAfisha.get();
        List<Image> uploadedImages = new ArrayList<>();

        for (MultipartFile file : files) {
            Image uploadedImage = uploadImageAfisha(file, name, afisha.getId());
            uploadedImages.add(uploadedImage);
        }

        return uploadedImages;
    }
}