package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.IncomeDto;

import java.util.List;

public interface IncomeService {

    IncomeDto add(IncomeDto incomeDto);

    IncomeDto getById(Long id);

    List<IncomeDto> getAll();

    IncomeDto update(IncomeDto incomeDto);

    void delete(Long id);
}
