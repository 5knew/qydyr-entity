package com.example.qydyr.controller;

import com.example.qydyr.model.Afisha;
import com.example.qydyr.model.Image;
import com.example.qydyr.model.Place;
import com.example.qydyr.repository.AfishaRepository;
import com.example.qydyr.repository.PlaceRepository;
import com.example.qydyr.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;
    private final PlaceRepository placeRepository;
    private final AfishaRepository afishaRepository;


    @PostMapping("/upload-place")
    public ResponseEntity<Image> uploadImagePlace(@RequestParam("file") MultipartFile file,
                                             @RequestParam("name") String name,
                                             @RequestParam("placeId") Long placeId) throws IOException {
        Optional<Place> optionalPlace = placeRepository.findById(placeId);
        Place place = optionalPlace.get();
        if (place == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Image uploadedImage = imageService.uploadImagePlace(file, name, placeId);
        return ResponseEntity.ok(uploadedImage);
    }

    @PostMapping("/upload-multiple-place")
    public ResponseEntity<List<Image>> uploadMultipleImagesPlace(@RequestParam("files") List<MultipartFile> files,
                                                            @RequestParam("name") String name,
                                                            @RequestParam("placeId") Long placeId) throws IOException {
        Optional<Place> optionalPlace = placeRepository.findById(placeId);
        Place place = optionalPlace.get();
        if (place == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<Image> uploadedImages = imageService.uploadMultipleImagesPlace(files, name, placeId);
        return ResponseEntity.ok(uploadedImages);
    }

    @PostMapping("/upload-afisha")
    public ResponseEntity<Image> uploadImageAfisha(@RequestParam("file") MultipartFile file,
                                             @RequestParam("name") String name,
                                             @RequestParam("afishaId") Long afishaId) throws IOException {
        Optional<Afisha> optionalAfisha = afishaRepository.findById(afishaId);
        Afisha afisha = optionalAfisha.get();
        if (afisha == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Image uploadedImage = imageService.uploadImageAfisha(file, name, afishaId);
        return ResponseEntity.ok(uploadedImage);
    }

    @PostMapping("/upload-multiple-afisha")
    public ResponseEntity<List<Image>> uploadMultipleImagesAfisha(@RequestParam("files") List<MultipartFile> files,
                                                            @RequestParam("name") String name,
                                                            @RequestParam("afishaId") Long afishaId) throws IOException {
        Optional<Afisha> optionalAfisha = afishaRepository.findById(afishaId);
        Afisha afisha = optionalAfisha.get();
        if (afisha == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<Image> uploadedImages = imageService.uploadMultipleImagesAfisha(files, name, afishaId);
        return ResponseEntity.ok(uploadedImages);
    }
}
