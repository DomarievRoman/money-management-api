package com.domariev.financialproject.controller;

import com.domariev.financialproject.dto.CostsDto;
import com.domariev.financialproject.model.Costs;
import com.domariev.financialproject.service.CostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/cashbook/costs")
public class CostsController {

    public final CostsService costsService;

    @PostMapping("/add/{cashbookId}")
    public ResponseEntity<CostsDto> addCosts(@RequestBody @Valid CostsDto costsDto, @PathVariable Long cashbookId) {
        return ResponseEntity.ok(costsService.add(costsDto, cashbookId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CostsDto> getCostsById(@PathVariable Long id) {
        return ResponseEntity.ok(costsService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCosts(@PathVariable Long id) {
        costsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
