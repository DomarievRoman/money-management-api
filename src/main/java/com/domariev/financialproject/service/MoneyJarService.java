package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.MoneyJarDto;

import java.util.List;

public interface MoneyJarService {

    MoneyJarDto create(MoneyJarDto moneyJarDto);

    MoneyJarDto getById(Long id);

    List<MoneyJarDto> getAll();

    MoneyJarDto update(MoneyJarDto moneyJarDto, Long id);

    void delete(Long id);
}
