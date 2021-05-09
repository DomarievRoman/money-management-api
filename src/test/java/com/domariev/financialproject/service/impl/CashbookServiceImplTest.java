package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.CashbookDto;
import com.domariev.financialproject.exception.ResourceNotFoundException;
import com.domariev.financialproject.mapper.CashbookMapper;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.CashbookStatistics;
import com.domariev.financialproject.model.Costs;
import com.domariev.financialproject.model.Income;
import com.domariev.financialproject.repository.CashbookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@Slf4j
class CashbookServiceImplTest {

    @Mock
    public CashbookRepository cashbookRepository;

    @InjectMocks
    public CashbookServiceImpl cashbookService;

    public final CashbookMapper cashbookMapper = Mappers.getMapper(CashbookMapper.class);

    @Test
    void create() {
        Cashbook cashbook = new Cashbook(1L, "Cashbook name", null, null, BigDecimal.valueOf(0));

        Mockito.when(cashbookRepository.save(any(Cashbook.class))).thenReturn(cashbook);

        Mockito.when(cashbookRepository.findById(1L)).thenReturn(Optional.of(cashbook));

        CashbookDto savedCashbook = cashbookService.create(cashbookMapper.cashbookToCashbookDto(cashbook));
        Mockito.verify(cashbookRepository).save(any(Cashbook.class));
        assertEquals(cashbook.getName(), savedCashbook.getName());
    }

    @Test
    void getById() {
        Cashbook cashbook = new Cashbook();
        cashbook.setId(100L);

        Mockito.when(cashbookRepository.findById(100L)).thenReturn(Optional.of(cashbook));

        CashbookDto foundedCashbook = cashbookService.getById(100L);
        Mockito.verify(cashbookRepository).findById(100L);

        assertEquals(100L, foundedCashbook.getId().longValue());
    }

    @Test
    void getAll() {
        Cashbook firstCashbook = new Cashbook(1L, "First cashbook", null, null, BigDecimal.valueOf(0));
        Cashbook secondCashbook = new Cashbook(2L, "Second cashbook", null, null, BigDecimal.valueOf(0));
        Cashbook thirdCashbook = new Cashbook(3L, "Third cashbook", null, null, BigDecimal.valueOf(0));

        List<Cashbook> expectedCashbookList = new ArrayList<>();
        expectedCashbookList.add(firstCashbook);
        expectedCashbookList.add(secondCashbook);
        expectedCashbookList.add(thirdCashbook);

        Mockito.when(cashbookRepository.findAll()).thenReturn(expectedCashbookList);

        List<CashbookDto> cashbookList = cashbookService.getAll();

        Assertions.assertEquals(expectedCashbookList.size(), cashbookList.size());
    }

    @Test
    void shouldThrowResourceNotFoundGetAll() {
        Throwable exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> cashbookService.getAll());
        assertEquals("There are no cashbooks yet", exception.getMessage());
    }


    @Test
    void update() {
        Cashbook cashbook = new Cashbook(1L, "Cashbook updatable name", null, null, BigDecimal.valueOf(0));
        Mockito.when(cashbookRepository.save(any(Cashbook.class))).thenReturn(cashbook);

        CashbookDto updatedCashbook = cashbookService.update(cashbookMapper.cashbookToCashbookDto(cashbook));
        Mockito.verify(cashbookRepository).save(any(Cashbook.class));
        assertEquals(cashbook.getName(), updatedCashbook.getName());
    }

    @Test
    void delete() {
        Cashbook cashbook = new Cashbook(1L, "Cashbook", null, null, BigDecimal.valueOf(0));

        Mockito.when(cashbookRepository.findById(1L)).thenReturn(Optional.of(cashbook));
        cashbookService.delete(1L);

        Mockito.verify(cashbookRepository, Mockito.times(1)).delete(cashbook);
    }

    @Test
    void getStatistics() {
        Cashbook cashbook = new Cashbook();
        Income firstIncome = new Income(1L, "flow purpose test", BigDecimal.valueOf(500), new Date(),
                "from test", true, cashbook);
        Income secondIncome = new Income(2L, "flow purpose test 2", BigDecimal.valueOf(410), new Date(),
                "from test 2", false, cashbook);
        List<Income> incomeList = new ArrayList<>();
        incomeList.add(firstIncome);
        incomeList.add(secondIncome);
        cashbook.setId(1L);
        cashbook.setName("test name");
        cashbook.setIncome(incomeList);
        Costs firstCost = new Costs(1L, "flow purpose test 3", BigDecimal.valueOf(800), new Date(),
                "from test 3", true, cashbook);
        Costs secondCost = new Costs(2L, "flow purpose test 4", BigDecimal.valueOf(360), new Date(),
                "from test 4", true, cashbook);
        List<Costs> costsList = new ArrayList<>();
        costsList.add(firstCost);
        costsList.add(secondCost);
        cashbook.setCosts(costsList);
        cashbook.setBalance(BigDecimal.valueOf(-250));

        Mockito.when(cashbookRepository.findById(1L)).thenReturn(Optional.of(cashbook));

        CashbookStatistics actualCashbookStatistics = cashbookService.getStatistics(1L);

        CashbookStatistics expectedCashbookStatistics = new CashbookStatistics(BigDecimal.valueOf(500),
                BigDecimal.valueOf(800), incomeList, costsList, cashbook);
        assertEquals(expectedCashbookStatistics, actualCashbookStatistics);
    }
}
