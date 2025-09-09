package com.example.qydyr.controller;

import com.example.qydyr.dto.CreateAfishaDto;
import com.example.qydyr.dto.UpdateAfishaDto;
import com.example.qydyr.exception.AfishaNotAvailableException;
import com.example.qydyr.exception.AfishaNotFoundException;
import com.example.qydyr.exception.InsufficientFundsException;
import com.example.qydyr.model.Afisha;
import com.example.qydyr.model.Place;
import com.example.qydyr.service.AfishaService;
import com.example.qydyr.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/afisha")
public class AfishaController {
    private final AfishaService afishaService;

    private final PaymentService paymentService;


    @PostMapping
    public ResponseEntity<?> createAfisha(@RequestBody CreateAfishaDto form) {
            afishaService.create(form);
            return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Afisha>> getAllAfishes() {
        List<Afisha> afishes = afishaService.getAll();
        return ResponseEntity.ok(afishes);
    }

    @GetMapping("/likes/{id}")
    public ResponseEntity<List<Afisha>> getAfishesByUserIdAndLikeId(@PathVariable Long id) {
        List<Afisha> afishes = afishaService.getAllLikes(id);
        return ResponseEntity.ok(afishes);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Afisha>> getAfishesByFilter(CreateAfishaDto afishaForm) {
        List<Afisha> afishes = afishaService.getAllByFilter(afishaForm);
        return ResponseEntity.ok(afishes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Afisha> getAfishaById(@PathVariable Long id) {
        Afisha afisha = afishaService.getById(id);
        return ResponseEntity.ok(afisha);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyAfisha(@RequestParam("userId") Long userId, @RequestParam("afishaId") Long afishaId) {
        try {
            paymentService.buyAfisha(userId, afishaId);
            return ResponseEntity.ok("Afisha successfully purchased.");
        } catch (AfishaNotFoundException | AfishaNotAvailableException | InsufficientFundsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Afisha> updateAfisha(@PathVariable Long id, @RequestBody UpdateAfishaDto updateAfishaDto) {
        try {
            Afisha updatedAfisha = afishaService.updateAfisha(id, updateAfishaDto);
            return new ResponseEntity<>(updatedAfisha, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteAfisha(@PathVariable Long id) {
        try {
            afishaService.deleteAfisha(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (AfishaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
