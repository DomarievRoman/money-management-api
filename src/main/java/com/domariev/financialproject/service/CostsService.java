package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.CostsDto;

import java.util.List;

public interface CostsService {

    CostsDto add(CostsDto costsDto, Long id);

    CostsDto getById(Long id);

    List<CostsDto> getAll();

    void delete(Long id);
}
