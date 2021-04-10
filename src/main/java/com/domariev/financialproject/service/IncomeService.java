package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.IncomeDto;
import com.domariev.financialproject.search.AbstractSearchValues;

import java.util.List;

public interface IncomeService {

    IncomeDto add(IncomeDto incomeDto);

    IncomeDto getById(Long id);

    List<IncomeDto> getAll();

    List<IncomeDto> search(AbstractSearchValues abstractSearchValues);

    IncomeDto update(IncomeDto incomeDto);

    void delete(Long id);
}
