package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.IncomeDto;
import com.domariev.financialproject.mapper.IncomeMapper;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.Income;
import com.domariev.financialproject.repository.CashbookRepository;
import com.domariev.financialproject.repository.IncomeRepository;
import com.domariev.financialproject.search.AbstractSearchValues;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@Slf4j
class IncomeServiceImplTest {

    @Mock
    public CashbookRepository cashbookRepository;

    @Mock
    public IncomeRepository incomeRepository;

    @InjectMocks
    public IncomeServiceImpl incomeService;

    public final IncomeMapper incomeMapper = Mappers.getMapper(IncomeMapper.class);

    @Test
    void add() {
        Cashbook cashbook = new Cashbook(1L, "test name", new ArrayList<>(),
                new ArrayList<>(), BigDecimal.ZERO);
        Income income = new Income(1L, "test flow purpose", BigDecimal.valueOf(400),
                new Date(), "test from", true, cashbook);

        Mockito.when(cashbookRepository.findById(1L))
                .thenReturn(Optional.of(cashbook));
        Mockito.when(incomeRepository.save(any(Income.class))).thenReturn(income);
        Mockito.when(incomeRepository.findById(1L)).thenReturn(Optional.of(income));

        IncomeDto actualIncome = incomeService.add(incomeMapper.incomeToIncomeDto(income));

        assertEquals(income.getFrom(), actualIncome.getFrom());
    }

    @Test
    void getById() {
        Income expectedIncome = new Income();
        expectedIncome.setId(100L);

        Mockito.when(incomeRepository.findById(100L)).thenReturn(Optional.of(expectedIncome));
        IncomeDto actualIncome = incomeService.getById(100L);
        assertEquals(expectedIncome.getId(), actualIncome.getId());
    }

    @Test
    void getAll() {
        Income firstIncome = new Income(1L, "test flow purpose", BigDecimal.valueOf(400),
                new Date(), "test from", true, new Cashbook());
        Income secondIncome = new Income(2L, "test flow purpose 2", BigDecimal.valueOf(175),
                new Date(), "test from 2", false, new Cashbook());

        List<Income> expectedIncomeList = new ArrayList<>();
        expectedIncomeList.add(firstIncome);
        expectedIncomeList.add(secondIncome);
        Mockito.when(incomeRepository.findAll()).thenReturn(expectedIncomeList);

        List<IncomeDto> actualIncomeList = incomeService.getAll();
        assertEquals(expectedIncomeList.size(), actualIncomeList.size());
    }

    @Test
    void search() {
        Income income = new Income(1L, "test flow purpose", BigDecimal.valueOf(400),
                new Date(), "test from", true, new Cashbook());

        Mockito.when(incomeRepository.findByFlowPurpose(income.getFlowPurpose()))
                .thenReturn(Collections.singletonList(income));

        AbstractSearchValues searchValue = new AbstractSearchValues();
        searchValue.setFlowPurpose("test flow purpose");
        List<IncomeDto> searchedIncomes = incomeService.search(searchValue);
        assertEquals(income.getFlowPurpose(), searchedIncomes.stream().map(IncomeDto::getFlowPurpose)
                .findFirst().get());
    }

    @Test
    void update() {
        Income expectedIncome = new Income(1L, "test flow purpose", BigDecimal.valueOf(400),
                new Date(), "test from", true, new Cashbook());
        Mockito.when(incomeRepository.save(any(Income.class))).thenReturn(expectedIncome);

        IncomeDto actualIncome = incomeService.update(incomeMapper.incomeToIncomeDto(expectedIncome));
        Mockito.verify(incomeRepository).save(any(Income.class));

        assertEquals(incomeMapper.incomeToIncomeDto(expectedIncome), actualIncome);
    }

    @Test
    void delete() {
        Income income = new Income(1L, "test flow purpose", BigDecimal.valueOf(400),
                new Date(), "test from", true, new Cashbook());
        Mockito.when(incomeRepository.findById(1L)).thenReturn(Optional.of(income));
        incomeService.delete(income.getId());

        Mockito.verify(incomeRepository, Mockito.times(1)).delete(income);
    }
}
