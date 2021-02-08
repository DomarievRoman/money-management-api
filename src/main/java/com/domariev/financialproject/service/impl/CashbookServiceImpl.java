package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.repository.CashbookRepository;
import com.domariev.financialproject.service.CashbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashbookServiceImpl implements CashbookService {

    private final CashbookRepository cashbookRepository;

    @Override
    public Cashbook create(Cashbook cashBook) {
        Cashbook newCashbook = new Cashbook();
        newCashbook.setName(cashBook.getName());
        newCashbook = cashbookRepository.save(newCashbook);
        if (cashbookRepository.findById(newCashbook.getId()).isPresent()) {
            log.info("create(): cashbook with id {}", newCashbook.getId());
        } else {
            log.info("create(): failed to create cashbook");
            throw new ResourceCreationException("Failed to create cashbook");
        }
        return newCashbook;
    }

    @Override
    public Cashbook getById(Long id)  {
        Cashbook cashBook = cashbookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found cashbook with id " + id));
        log.info("get(): cashbook id " + id);
        return cashBook;
    }


    @Override
    public void delete(Long id) {
        Cashbook cashBook = getById(id);
        cashbookRepository.delete(cashBook);
        log.info("delete(): cashbook id " + id + " deleted");
    }
}