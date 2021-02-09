package com.domariev.financialproject.controller;

import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.service.CashbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/cashbook")
public class CashbookController {

    private final CashbookService cashbookService;

    @PostMapping("/create")
    public ResponseEntity<Cashbook> createCashbook(@RequestBody Cashbook cashbook) {
        return ResponseEntity.ok(cashbookService.create(cashbook));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Cashbook> getCashbookById(@PathVariable Long id) {
        return ResponseEntity.ok(cashbookService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCashbook(@PathVariable Long id) {
        cashbookService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
