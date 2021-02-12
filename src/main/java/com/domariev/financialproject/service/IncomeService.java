package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.IncomeDto;

import java.util.List;

public interface IncomeService {

    IncomeDto add(IncomeDto incomeDto, Long id);

    IncomeDto getById(Long id);

    List<IncomeDto> getAll();

    IncomeDto update(IncomeDto incomeDto, Long id);

    void delete(Long id);
}
