package com.domariev.financialproject.controller;

import com.domariev.financialproject.model.Income;
import com.domariev.financialproject.service.IncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/cashbook/income")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping("/add/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Income addIncome(@RequestBody Income income, @PathVariable Long id) {
        return incomeService.add(income, id);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Income getIncomeById(@PathVariable Long id) {
        return incomeService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteIncome(@PathVariable Long id) {
        incomeService.delete(id);
    }
}
