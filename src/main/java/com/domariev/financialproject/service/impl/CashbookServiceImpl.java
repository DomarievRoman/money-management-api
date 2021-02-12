package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.CashbookDto;
import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.mapper.CashbookMapper;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.repository.CashbookRepository;
import com.domariev.financialproject.service.CashbookService;
import com.domariev.financialproject.util.CashbookBalanceCounter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashbookServiceImpl implements CashbookService {

    private final CashbookRepository cashbookRepository;

    private final CashbookMapper cashbookMapper = Mappers.getMapper(CashbookMapper.class);

    @Override
    public CashbookDto create(CashbookDto cashbookDto) {
        Cashbook cashbook = cashbookMapper.cashbookDtoToCashbook(cashbookDto);
        Cashbook newCashbook = new Cashbook();
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
    public CashbookDto getById(Long id)  {
        Cashbook cashbook = cashbookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found cashbook with id " + id));
        log.info("get(): cashbook id " + id);
        CashbookBalanceCounter.countBalance(cashbook);
        cashbookRepository.save(cashbook);
        return cashbookMapper.cashbookToCashbookDto(cashbook);
    }

    @Override
    public List<CashbookDto> getAll() {
        List<Cashbook> cashbookList = cashbookRepository.findAll();
        if (cashbookList.isEmpty()) {
            throw new ResourceNotFoundException("There are no cashbooks yet");
        } else {
            log.info("getAll(): retrieved all cashbooks");
            return cashbookMapper.cashbookListToDto(cashbookList);
        }
    }

    @Override
    public CashbookDto update(CashbookDto cashbookDto, Long id) {
        CashbookDto newCashBook = getById(id);
        newCashBook.setName(cashbookDto.getName());
        Cashbook cashbook = cashbookMapper.cashbookDtoToCashbook(newCashBook);
        cashbook = cashbookRepository.save(cashbook);
        log.info("update(): new cashbook with id " + id);
        return cashbookMapper.cashbookToCashbookDto(cashbook);

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
