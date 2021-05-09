package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.CashbookDto;
import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.mapper.CashbookMapper;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.CashbookStatistics;
import com.domariev.financialproject.model.Costs;
import com.domariev.financialproject.model.Income;
import com.domariev.financialproject.repository.CashbookRepository;
import com.domariev.financialproject.service.CashbookService;
import com.domariev.financialproject.util.CashbookBalanceCounter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    public CashbookDto getById(Long id) {
        Cashbook cashbook = cashbookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found cashbook with id " + id));
        log.info("get(): cashbook id " + id);
        return cashbookMapper.cashbookToCashbookDto(cashbook);
    }

    @Override
    public List<CashbookDto> getAll() {
        List<Cashbook> cashbookList = cashbookRepository.findAll();
        for (Cashbook cashbook : cashbookList) {
            if (cashbook.getIncome() != null && cashbook.getCosts() != null) {
                CashbookBalanceCounter.countBalance(cashbook);
                cashbookRepository.save(cashbook);
            }
        }
        if (cashbookList.isEmpty()) {
            throw new ResourceNotFoundException("There are no cashbooks yet");
        } else {
            log.info("getAll(): retrieved all cashbooks");
            return cashbookMapper.cashbookListToDto(cashbookList);
        }
    }

    @Override
    public CashbookDto update(CashbookDto cashbookDto) {
        CashbookDto newCashBook = new CashbookDto();
        newCashBook.setId(cashbookDto.getId());
        newCashBook.setName(cashbookDto.getName());
        Cashbook cashbook = cashbookMapper.cashbookDtoToCashbook(newCashBook);
        cashbook = cashbookRepository.save(cashbook);
        log.info("update(): new cashbook with id " + cashbook.getId());
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

    @Override
    public CashbookStatistics getStatistics(Long id) {
        CashbookStatistics statistics = new CashbookStatistics();
        Cashbook cashbook = cashbookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found cashbook with id " + id));
        BigDecimal largestIncomePayment = cashbook.getIncome().stream()
                .map(Income::getPayment).max(BigDecimal::compareTo).orElse(null);
        statistics.setLargestIncomePayment(largestIncomePayment);
        BigDecimal largestCostPayment = cashbook.getCosts().stream()
                .map(Costs::getPayment).max(BigDecimal::compareTo).orElse(null);
        statistics.setLargestCostPayment(largestCostPayment);
        LocalDate date = LocalDate.now();
        String curMonth = String.valueOf(date.getMonthValue());

        List<Income> incomeList = cashbook.getIncome();
        List<Income> incomesByCurrentMonth = new ArrayList<>();
        for (Income income : incomeList) {
            String incomeMonth = String.valueOf(income.getTransactionDate().
                    toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue());
            if (incomeMonth.equals(curMonth)) {
                incomesByCurrentMonth.add(income);
            }
        }
        statistics.setAllIncomesByMonth(incomesByCurrentMonth);

        List<Costs> costList = cashbook.getCosts();
        List<Costs> costsByCurrentMonth = new ArrayList<>();
        for (Costs c : costList) {
            String costMonth = String.valueOf(c.getTransactionDate()
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue());
            if (costMonth.equals(curMonth)) {
                costsByCurrentMonth.add(c);
            }
        }
        statistics.setAllCostsByMonth(costsByCurrentMonth);
        statistics.setCashbook(cashbook);
        log.info("getStatistics(): get statistics of cashbook with id " + id);
        return statistics;
    }
}
