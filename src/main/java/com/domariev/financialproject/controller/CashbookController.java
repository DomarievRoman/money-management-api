package com.domariev.financialproject.controller;

import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.service.CashbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/cashbook")
public class CashbookController {

    private final CashbookService cashbookService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Cashbook createCashbook(@RequestBody Cashbook cashbook) {
        return cashbookService.create(cashbook);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cashbook getCashbookById(@PathVariable Long id) {
        return cashbookService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCashbook(@PathVariable Long id) {
        cashbookService.delete(id);
    }
}
