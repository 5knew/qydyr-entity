package com.example.qydyr.service.impl;

import com.example.qydyr.dto.CreateAfishaDto;
import com.example.qydyr.dto.UpdateAfishaDto;
import com.example.qydyr.exception.AfishaNotFoundException;
import com.example.qydyr.model.Address;
import com.example.qydyr.model.Afisha;
import com.example.qydyr.model.Image;
import com.example.qydyr.model.enums.EventCategory;
import com.example.qydyr.model.enums.Status;
import com.example.qydyr.repository.AfishaRepository;
import com.example.qydyr.service.AfishaService;
import com.example.qydyr.service.GeoLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AfishaServiceImpl implements AfishaService {
    private final AfishaRepository afishaRepository;
    private final GeoLocationService geoLocationService;

    @Transactional
    public void create(CreateAfishaDto form) {
        final Afisha afisha = new Afisha(form.getName(), form.getAddressName(), form.getAddressLink(), form.getCreatedDateTime(), form.getEventDateTimeFrom(), form.getEventDateTimeTo(),
                form.getPhone(), form.getDescription(), form.getPrice(),
                new Address(form.getStreet(), form.getCity(), form.getCountry()),
                form.getStatus(), form.getCategory(), form.getGeoProcessed());
        this.computeAddress(afisha);
        afishaRepository.save(afisha);
    }

    @Override
    public List<Afisha> getAll() {
        final  List<Afisha> afishas = afishaRepository.findAll();
        afishas.forEach(this::computeAddress);
        return afishas;
    }

    @Override
    public Page<Afisha> getAllAfisha(Pageable pageable) {
        Page<Afisha> afishas = afishaRepository.findAll(pageable);
        afishas.getContent().forEach(this::computeAddress);
        return afishas;
    }
    @Override
    public Optional<Afisha> getAfishaById(Long id) {
        return afishaRepository.findById(id);
    }

    @Override
    public List<Afisha> getAllLikes(Long id) {
        final List<Afisha> afishas = afishaRepository.findAllAfishesByUserIdAndLikeId(id);
        afishas.forEach(this::computeAddress);
        return afishas;
    }

    @Override
    public List<Afisha> getAllByFilter(CreateAfishaDto afishaForm) {
        final List<Afisha> afishas = afishaRepository.findAfishesByStatusAndCategory(afishaForm.getCategory(), afishaForm.getStatus());
        afishas.forEach(this::computeAddress);
        return afishas;
    }

    @Override
    public Page<Afisha> getAfishesBySearchAndFilter(String search, EventCategory category, Status status, Pageable pageable) {
        Page<Afisha> afishas = afishaRepository.findAfishesBySearchAndFilter(search, category, status, pageable);
        afishas.getContent().forEach(this::computeAddress);
        return afishas;
    }

    @Override
    public Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    @Override
    public Afisha getById(Long id) {
        return afishaRepository.findById(id)
                .orElseThrow(() -> new AfishaNotFoundException("Afisha with id " + id + " not found"));
    }
    @Transactional
    public Afisha updateAfisha(Long id, UpdateAfishaDto form) {
        Optional<Afisha> optionalAfisha = afishaRepository.findById(id);
        if (optionalAfisha.isPresent()) {
            Afisha afisha = optionalAfisha.get();
            afisha.setName(form.getName());
            afisha.setAddressName(form.getAddressName());
            afisha.setAddressLink(form.getAddressLink());
            afisha.setCreatedDateTime(form.getCreatedDateTime());
            afisha.setEventDateTimeFrom(form.getEventDateTimeFrom());
            afisha.setEventDateTimeTo(form.getEventDateTimeTo());
            afisha.setPhone(form.getPhone());
            afisha.setDescription(form.getDescription());
            afisha.setPrice(form.getPrice());
            afisha.setAddress(new Address(form.getStreet(), form.getCity(), form.getCountry()));
            afisha.setStatus(form.getStatus());
            afisha.setCategory(form.getCategory());
            afisha.setGeoProcessed(form.getGeoProcessed());
            this.computeAddress(afisha);
            return afishaRepository.save(afisha);
        } else {
            throw new IllegalArgumentException("Afisha with id " + id + " does not exist");
        }
    }

    @Override
    public void deleteAfisha(Long id) {
        afishaRepository.deleteById(id);
    }

    @Override
    public Afisha saveAfisha(Afisha afisha) {
        return afishaRepository.save(afisha);
    }

    @Override
    public long count() {
        return afishaRepository.count();
    }

    private void computeAddress(Afisha afisha) {
        if (afisha.getAddress() != null && afisha.getGeoLocation() == null && !afisha.isGeoProcessed()) {
            geoLocationService.computeGeoLocation(afisha.getAddress().toString())
                    .ifPresent(afisha::setGeoLocation);
            afisha.setGeoProcessed(true);
            afishaRepository.save(afisha);
        }
    }
}
