package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.CashbookDto;
import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.mapper.CashbookMapper;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.repository.CashbookRepository;
import com.domariev.financialproject.service.CashbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashbookServiceImpl implements CashbookService {

    private final CashbookRepository cashbookRepository;

    private final CashbookMapper cashbookMapper = Mappers.getMapper(CashbookMapper.class);

    @Override
    public CashbookDto create(CashbookDto cashbookDto) {
        Cashbook cashbook = cashbookMapper.cashbookDtoToCashbook(cashbookDto);
        Cashbook newCashbook = cashbookMapper.cashbookDtoToCashbook(cashbookDto);
        newCashbook.setName(cashbook.getName());
        newCashbook = cashbookRepository.save(newCashbook);
        if (cashbookRepository.findById(newCashbook.getId()).isPresent()) {
            log.info("create(): cashbook with id {}", newCashbook.getId());
        } else {
            log.info("create(): failed to create cashbook");
            throw new ResourceCreationException("Failed to create cashbook");
        }
        return cashbookMapper.cashbookToCashbookDto(newCashbook);
    }

    @Override
    public Cashbook getById(Long id)  {
        Cashbook cashBook = cashbookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found cashbook with id " + id));
        log.info("get(): cashbook id " + id);
        return cashbookRepository.save(cashBook);
    }


    @Override
    public void delete(Long id) {
        Cashbook cashBook = cashbookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found cashbook with id " + id));
        log.info("get(): cashbook id " + id);
        cashbookRepository.delete(cashBook);
        log.info("delete(): cashbook id " + id + " deleted");
    }
}
