package com.example.qydyr.service;

import com.example.qydyr.dto.CreateAfishaDto;
import com.example.qydyr.dto.UpdateAfishaDto;
import com.example.qydyr.model.Afisha;
import com.example.qydyr.model.Image;
import com.example.qydyr.model.enums.EventCategory;
import com.example.qydyr.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AfishaService {
    void create(CreateAfishaDto form);
    List<Afisha> getAll();
    Page<Afisha> getAllAfisha(Pageable pageable);
    Optional<Afisha> getAfishaById(Long id);

    List<Afisha> getAllLikes(Long id);

    List<Afisha> getAllByFilter(CreateAfishaDto afishaForm);

    Page<Afisha> getAfishesBySearchAndFilter(String search, EventCategory category, Status status, Pageable pageable);

    Image toImageEntity(MultipartFile file) throws IOException;

    Afisha getById(Long id);

    Afisha updateAfisha(Long id, UpdateAfishaDto form);

    void deleteAfisha(Long id);
    
    Afisha saveAfisha(Afisha afisha);
    long count();
}
