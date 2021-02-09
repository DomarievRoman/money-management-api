package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.CashbookDto;
import com.domariev.financialproject.model.Cashbook;

public interface CashbookService {

    CashbookDto create(CashbookDto cashbookDto);

    Cashbook getById(Long id);

    void delete(Long id);

}
