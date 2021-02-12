package com.domariev.financialproject.controller;

import com.domariev.financialproject.dto.ContributionDto;
import com.domariev.financialproject.service.ContributionService;
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
@RequestMapping("/api/v1/moneyJar/contribution")
public class ContributionController {

    private final ContributionService contributionService;

    @PostMapping("/add/{contributionId}")
    public ResponseEntity<ContributionDto> addContribution
            (@RequestBody @Valid ContributionDto contributionDto, @PathVariable Long contributionId) {
        return ResponseEntity.ok(contributionService.add(contributionDto, contributionId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ContributionDto> getContributionById(@PathVariable Long id) {
        return ResponseEntity.ok(contributionService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ContributionDto>> getAllContributions() {
        return ResponseEntity.ok(contributionService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContributionDto> updateContribution(@RequestBody ContributionDto contributionDto, @PathVariable Long id) {
        return ResponseEntity.ok(contributionService.update(contributionDto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteContribution(@PathVariable Long id) {
        contributionService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
