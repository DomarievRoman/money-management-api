package com.domariev.financialproject.controller;

import com.domariev.financialproject.model.Costs;
import com.domariev.financialproject.service.CostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/cashbook/costs")
public class CostsController {

    public final CostsService costsService;

    @PostMapping("/add/{id}")
    public ResponseEntity<Costs> addCosts(@RequestBody Costs costs, @PathVariable Long id) {
        return ResponseEntity.ok(costsService.add(costs, id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Costs> getCostsById(@PathVariable Long id) {
        return ResponseEntity.ok(costsService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCosts(@PathVariable Long id) {
        costsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
