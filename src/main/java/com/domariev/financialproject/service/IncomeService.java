package com.domariev.financialproject.service;

import com.domariev.financialproject.model.Income;

public interface IncomeService {

    Income add(Income income, Long id);

    Income getById(Long id);

    void delete(Long id);
}
