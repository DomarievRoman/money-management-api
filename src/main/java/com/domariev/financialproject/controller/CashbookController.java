package com.domariev.financialproject.controller;

import com.domariev.financialproject.dto.CashbookDto;
import com.domariev.financialproject.service.CashbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/cashbook")
public class CashbookController {

    private final CashbookService cashbookService;

    @PostMapping("/create")
    public ResponseEntity<CashbookDto> createCashbook(@RequestBody @Valid CashbookDto cashbookDto) {
        return ResponseEntity.ok(cashbookService.create(cashbookDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CashbookDto> getCashbookById(@PathVariable Long id) {
        return ResponseEntity.ok(cashbookService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CashbookDto>> getAllCashbooks() {
        return ResponseEntity.ok(cashbookService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<CashbookDto> updateCashbook(@RequestBody CashbookDto cashbookDto) {
        return ResponseEntity.ok(cashbookService.update(cashbookDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCashbook(@PathVariable Long id) {
        cashbookService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
