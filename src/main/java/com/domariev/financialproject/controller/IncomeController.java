package com.domariev.financialproject.controller;

import com.domariev.financialproject.dto.IncomeDto;
import com.domariev.financialproject.service.IncomeService;
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
@RequestMapping("/api/v1/cashbook/income")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping("/add")
    public ResponseEntity<IncomeDto> addIncome(@RequestBody @Valid IncomeDto incomeDto) {
        return ResponseEntity.ok(incomeService.add(incomeDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<IncomeDto> getIncomeById(@PathVariable Long id) {
        return ResponseEntity.ok(incomeService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<IncomeDto>> getAllIncomes() {
        return ResponseEntity.ok(incomeService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<IncomeDto> updateIncome(@RequestBody IncomeDto incomeDto) {
        return ResponseEntity.ok(incomeService.update(incomeDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteIncome(@PathVariable Long id) {
        incomeService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
