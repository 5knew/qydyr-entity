package com.example.qydyr.controller;

import com.example.qydyr.model.Image;
import com.example.qydyr.model.Place;
import com.example.qydyr.repository.ImageRepository;
import com.example.qydyr.repository.PlaceRepository;
import com.example.qydyr.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final PlaceRepository placeRepository;


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


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(image.getContentType()));
            headers.setContentLength(image.getSize());
            return new ResponseEntity<>(image.getBytes(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
