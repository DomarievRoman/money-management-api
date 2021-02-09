package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.IncomeDto;

public interface IncomeService {

    IncomeDto add(IncomeDto incomeDto, Long id);

    IncomeDto getById(Long id);

    void delete(Long id);
}
