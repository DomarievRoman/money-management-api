package com.domariev.financialproject.controller;

import com.domariev.financialproject.dto.WishDto;
import com.domariev.financialproject.service.WishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/wish")
public class WishController {

    private final WishService wishService;

    @PostMapping("/create")
    public ResponseEntity<WishDto> createWish(@RequestBody WishDto wishDto) {
        return ResponseEntity.ok(wishService.create(wishDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<WishDto> getWish(@PathVariable Long id) {
        return ResponseEntity.ok(wishService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<WishDto>> getAllWishes() {
        return ResponseEntity.ok(wishService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WishDto> updateWish(@RequestBody WishDto wishDto, @PathVariable Long id) {
        return ResponseEntity.ok(wishService.update(wishDto, id));
    }

    @PutMapping("/setImplemented/{id}/{condition}")
    public ResponseEntity<WishDto> setWishImplemented(@PathVariable Long id, @PathVariable Boolean condition) {
        return ResponseEntity.ok(wishService.updateImplementedStatus(condition, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteWish(@PathVariable Long id) {
        wishService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
