package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.IncomeDto;
import com.domariev.financialproject.exception.ResourceCreationException;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.mapper.IncomeMapper;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.Income;
import com.domariev.financialproject.repository.CashbookRepository;
import com.domariev.financialproject.repository.IncomeRepository;
import com.domariev.financialproject.search.AbstractSearchValues;
import com.domariev.financialproject.service.IncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class IncomeServiceImpl implements IncomeService {

    private final CashbookRepository cashbookRepository;

    private final IncomeRepository incomeRepository;

    private final IncomeMapper incomeMapper = Mappers.getMapper(IncomeMapper.class);

    @Override
    public IncomeDto add(IncomeDto incomeDto) {
        Income income = incomeMapper.incomeDtoToIncome(incomeDto);
        Income newIncome = new Income();
        Cashbook cashbook = cashbookRepository.findById(income.getCashbook().getId())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Not found cashbook with id " + income.getCashbook().getId()));
        newIncome.setFlowPurpose(income.getFlowPurpose());
        newIncome.setPayment(income.getPayment());
        newIncome.setTransactionDate(income.getTransactionDate());
        newIncome.setFrom(income.getFrom());
        newIncome.setRegular(income.getRegular());
        newIncome.setCashbook(income.getCashbook());
        cashbook.getIncome().add(newIncome);
        newIncome = incomeRepository.save(newIncome);
        if (incomeRepository.findById(newIncome.getId()).isPresent()) {
            log.info("add(): income with id " + newIncome.getId());
        } else {
            log.info("add(): failed to add income");
            throw new ResourceCreationException("Failed to add income");
        }
        return incomeMapper.incomeToIncomeDto(newIncome);
    }

    @Override
    public IncomeDto getById(Long id) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found income with id " + id));
        log.info("get(): cashbook id " + id);
        return incomeMapper.incomeToIncomeDto(income);
    }

    @Override
    public List<IncomeDto> getAll() {
        List<Income> incomeList = incomeRepository.findAll();
        if (incomeList.isEmpty()) {
            throw new ResourceNotFoundException("There are no incomes yet");
        } else {
            log.info("getAll(): retrieved all incomes");
            return incomeMapper.incomeListToDto(incomeList);
        }
    }

    @Override
    public List<IncomeDto> search(AbstractSearchValues abstractSearchValues) {
        List<Income> incomeList = incomeRepository.findByFlowPurpose(abstractSearchValues.getFlowPurpose());
        if (incomeList.isEmpty()) {
            throw new ResourceNotFoundException("There are no wanted incomes");
        } else {
            log.info("search(): all found incomes");
            return incomeMapper.incomeListToDto(incomeList);
        }
    }

    @Override
    public IncomeDto update(IncomeDto incomeDto) {
        IncomeDto newIncomeDto = new IncomeDto();
        newIncomeDto.setId(incomeDto.getId());
        newIncomeDto.setFlowPurpose(incomeDto.getFlowPurpose());
        newIncomeDto.setPayment(incomeDto.getPayment());
        newIncomeDto.setFrom(incomeDto.getFrom());
        newIncomeDto.setTransactionDate(incomeDto.getTransactionDate());
        newIncomeDto.setRegular(incomeDto.getRegular());
        newIncomeDto.setCashbook(incomeDto.getCashbook());
        Income income = incomeMapper.incomeDtoToIncome(newIncomeDto);
        income = incomeRepository.save(income);
        log.info("update(): income with id " + income.getId());
        return incomeMapper.incomeToIncomeDto(income);
    }

    @Override
    public void delete(Long id) {
        IncomeDto incomeDto = getById(id);
        Income income = incomeMapper.incomeDtoToIncome(incomeDto);
        incomeRepository.delete(income);
        log.info("delete(): income id " + id + " deleted");
    }
}
