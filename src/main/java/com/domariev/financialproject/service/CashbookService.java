package com.domariev.financialproject.service;

import com.domariev.financialproject.dto.CashbookDto;

import java.util.List;

public interface CashbookService {

    CashbookDto create(CashbookDto cashbookDto);

    CashbookDto getById(Long id);

    List<CashbookDto> getAll();

    CashbookDto update(CashbookDto cashbookDto, Long id);

    void delete(Long id);

}
