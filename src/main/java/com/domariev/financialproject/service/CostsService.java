package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.CostsDto;

public interface CostsService {

    CostsDto add(CostsDto costsDto, Long id);

    CostsDto getById(Long id);

    void delete(Long id);
}
