package com.domariev.financialproject.service;

import com.domariev.financialproject.model.Costs;

public interface CostsService {

    Costs add(Costs costs, Long id);

    Costs getById(Long id);

    void delete(Long id);
}
