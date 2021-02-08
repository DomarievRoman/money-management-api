package com.domariev.financialproject.service;

import com.domariev.financialproject.model.Cashbook;

public interface CashbookService {

    Cashbook create(Cashbook cashBook);

    Cashbook getById(Long id);

    void delete(Long id);

}
