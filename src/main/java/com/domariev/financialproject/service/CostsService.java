package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.CostsDto;
import com.domariev.financialproject.search.AbstractSearchValues;

import java.util.List;

public interface CostsService {

    CostsDto add(CostsDto costsDto);

    CostsDto getById(Long id);

    List<CostsDto> getAll();

    List<CostsDto> search(AbstractSearchValues abstractSearchValues);

    CostsDto update(CostsDto costsDto);

    void delete(Long id);
}
