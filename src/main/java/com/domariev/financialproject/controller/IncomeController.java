package com.domariev.financialproject.controller;

import com.domariev.financialproject.dto.IncomeDto;
import com.domariev.financialproject.model.Income;
import com.domariev.financialproject.service.IncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/cashbook/income")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping("/add/{cashbookId}")
    public ResponseEntity<IncomeDto> addIncome(@RequestBody @Valid IncomeDto incomeDto, @PathVariable Long cashbookId) {
        return ResponseEntity.ok(incomeService.add(incomeDto, cashbookId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<IncomeDto> getIncomeById(@PathVariable Long id) {
        return ResponseEntity.ok(incomeService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteIncome(@PathVariable Long id) {
        incomeService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
