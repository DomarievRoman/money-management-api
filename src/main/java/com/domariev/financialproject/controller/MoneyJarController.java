package com.domariev.financialproject.controller;

import com.domariev.financialproject.dto.MoneyJarDto;
import com.domariev.financialproject.service.MoneyJarService;
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
@RequestMapping("/api/v1/moneyJar")
public class MoneyJarController {

    private final MoneyJarService moneyJarService;

    @PostMapping("/create")
    public ResponseEntity<MoneyJarDto> createMoneyJar(@RequestBody @Valid MoneyJarDto moneyJarDto) {
        return ResponseEntity.ok(moneyJarService.create(moneyJarDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MoneyJarDto> getMoneyJarById(@PathVariable Long id) {
        return ResponseEntity.ok(moneyJarService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MoneyJarDto>> getAllMoneyJars() {
        return ResponseEntity.ok(moneyJarService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MoneyJarDto> updateWish(@RequestBody MoneyJarDto moneyJarDto, @PathVariable Long id) {
        return ResponseEntity.ok(moneyJarService.update(moneyJarDto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMoneyJar(@PathVariable Long id) {
        moneyJarService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
