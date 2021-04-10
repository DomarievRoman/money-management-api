package com.domariev.financialproject.controller;

import com.domariev.financialproject.dto.CostsDto;
import com.domariev.financialproject.search.AbstractSearchValues;
import com.domariev.financialproject.service.CostsService;
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
@RequestMapping("/api/v1/cashbook/costs")
public class CostsController {

    public final CostsService costsService;

    @PostMapping("/add")
    public ResponseEntity<CostsDto> addCosts(@RequestBody @Valid CostsDto costsDto) {
        return ResponseEntity.ok(costsService.add(costsDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CostsDto> getCostsById(@PathVariable Long id) {
        return ResponseEntity.ok(costsService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CostsDto>> getAllCosts() {
        return ResponseEntity.ok(costsService.getAll());
    }

    @PostMapping("/search")
    public ResponseEntity<List<CostsDto>> searchCosts(@RequestBody AbstractSearchValues abstractSearchValues) {
        return ResponseEntity.ok(costsService.search(abstractSearchValues));
    }

    @PutMapping("/update")
    public ResponseEntity<CostsDto> updateCosts(@RequestBody CostsDto costsDto) {
        return ResponseEntity.ok(costsService.update(costsDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCosts(@PathVariable Long id) {
        costsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
