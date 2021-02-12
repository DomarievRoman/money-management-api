package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.ContributionDto;

import java.util.List;

public interface ContributionService {

    ContributionDto add(ContributionDto contributionDto, Long id);

    ContributionDto getById(Long id);

    List<ContributionDto> getAll();

    ContributionDto update(ContributionDto contributionDto, Long id);

    void delete(Long id);
}
