package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.CostsDto;

import java.util.List;

public interface CostsService {

    CostsDto add(CostsDto costsDto);

    CostsDto getById(Long id);

    List<CostsDto> getAll();

    CostsDto update(CostsDto costsDto);

    void delete(Long id);
}
