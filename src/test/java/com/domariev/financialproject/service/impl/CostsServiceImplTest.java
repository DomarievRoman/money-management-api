package com.domariev.financialproject.service.impl;

import com.domariev.financialproject.dto.CostsDto;
import com.domariev.financialproject.mapper.CostsMapper;
import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.Costs;
import com.domariev.financialproject.repository.CashbookRepository;
import com.domariev.financialproject.repository.CostsRepository;
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
class CostsServiceImplTest {

    @Mock
    public CashbookRepository cashbookRepository;

    @Mock
    public CostsRepository costsRepository;

    @InjectMocks
    public CostsServiceImpl costsService;

    public final CostsMapper costsMapper = Mappers.getMapper(CostsMapper.class);

    @Test
    void add() {
        Cashbook cashbook = new Cashbook(1L, "test name", new ArrayList<>(),
                new ArrayList<>(), BigDecimal.ZERO);
        Costs expectedCosts = new Costs(1L, "test flow purpose", BigDecimal.valueOf(124),
                new Date(), "test to", true, cashbook);
        Mockito.when(cashbookRepository.findById(1L)).thenReturn(Optional.of(cashbook));
        Mockito.when(costsRepository.save(any(Costs.class))).thenReturn(expectedCosts);
        Mockito.when(costsRepository.findById(1L)).thenReturn(Optional.of(expectedCosts));

        CostsDto actualCosts = costsService.add(costsMapper.costsToCostsDto(expectedCosts));
        assertEquals(costsMapper.costsToCostsDto(expectedCosts), actualCosts);
    }

    @Test
    void getById() {
        Costs expectedCosts = new Costs();
        expectedCosts.setId(100L);

        Mockito.when(costsRepository.findById(100L)).thenReturn(Optional.of(expectedCosts));
        CostsDto actualCosts = costsService.getById(100L);

        assertEquals(expectedCosts.getId(), actualCosts.getId());
    }

    @Test
    void getAll() {
        Costs firstCosts = new Costs(1L, "test flow purpose", BigDecimal.valueOf(124), new Date(),
                "test to", true, new Cashbook());
        Costs secondCosts = new Costs(2L, "test flow purpose 2", BigDecimal.valueOf(124), new Date(),
                "test to 2", false, new Cashbook());

        List<Costs> expectedCostsList = new ArrayList<>();
        expectedCostsList.add(firstCosts);
        expectedCostsList.add(secondCosts);

        Mockito.when(costsRepository.findAll()).thenReturn(expectedCostsList);

        List<CostsDto> actualCostsList = costsService.getAll();

        assertEquals(expectedCostsList.size(), actualCostsList.size());
    }

    @Test
    void search() {
        Costs expectedSearchedCosts = new Costs(1L, "test flow purpose", BigDecimal.valueOf(124),
                new Date(), "test to", true, new Cashbook());

        Mockito.when(costsRepository.findByFlowPurpose("test flow purpose"))
                .thenReturn(Collections.singletonList(expectedSearchedCosts));

        AbstractSearchValues searchValue = new AbstractSearchValues();
        searchValue.setFlowPurpose("test flow purpose");
        List<CostsDto> actualSearchedCosts = costsService.search(searchValue);

        assertEquals(expectedSearchedCosts.getFlowPurpose(), actualSearchedCosts.stream().map(CostsDto::getFlowPurpose)
                .findFirst().get());
    }

    @Test
    void update() {
        Costs expectedCosts = new Costs(1L, "test flow purpose", BigDecimal.valueOf(124),
                new Date(), "test to", true, new Cashbook());
        Mockito.when(costsRepository.save(any(Costs.class))).thenReturn(expectedCosts);

        CostsDto actualCosts = costsService.update(costsMapper.costsToCostsDto(expectedCosts));

        Mockito.verify(costsRepository).save(any(Costs.class));
        assertEquals(costsMapper.costsToCostsDto(expectedCosts), actualCosts);
    }

    @Test
    void delete() {
        Costs expectedCosts = new Costs(1L, "test flow purpose", BigDecimal.valueOf(124),
                new Date(), "test to", true, new Cashbook());

        Mockito.when(costsRepository.findById(1L)).thenReturn(Optional.of(expectedCosts));
        costsService.delete(expectedCosts.getId());

        Mockito.verify(costsRepository, Mockito.times(1)).delete(expectedCosts);
    }
}
