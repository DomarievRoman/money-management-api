package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.Income;
import com.domariev.financialproject.repository.CashbookRepository;
import com.domariev.financialproject.repository.IncomeRepository;
import com.domariev.financialproject.service.IncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class IncomeServiceImpl implements IncomeService {

    private final CashbookRepository cashbookRepository;

    private final IncomeRepository incomeRepository;

    @Override
    public Income add(Income income, Long id) {
        Income newIncome = new Income();
        Cashbook cashBook = cashbookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found cashbook with id " + id));
        newIncome.setFlowPurpose(income.getFlowPurpose());
        newIncome.setPayment(income.getPayment());
        newIncome.setTransactionDate(LocalDateTime.now());
        newIncome.setFrom(income.getFrom());
        if (income.getRegular() == null) {
            newIncome.setRegular(false);
        } else {
            newIncome.setRegular(income.getRegular());
        }
        cashBook.getIncome().add(newIncome);
        newIncome = incomeRepository.save(newIncome);
        if (incomeRepository.findById(newIncome.getId()).isPresent()) {
            log.info("add(): income with id " + id);
        } else {
            log.info("add(): failed to add income");
            throw new ResourceCreationException("Failed to add income");
        }
        return newIncome;
    }

    @Override
    public Income getById(Long id) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found cashbook with id " + id));
        log.info("get(): cashbook id " + id);
        return income;
    }

    @Override
    public void delete(Long id) {
        Income income = getById(id);
        incomeRepository.delete(income);
        log.info("delete(): income id " + id + " deleted");
    }
}
