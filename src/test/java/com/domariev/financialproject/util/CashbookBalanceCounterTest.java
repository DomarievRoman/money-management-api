package com.domariev.financialproject.util;

import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.Costs;
import com.domariev.financialproject.model.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class CashbookBalanceCounterTest {

    public Cashbook cashbook = new Cashbook();

    @BeforeEach
    public void init() {
        cashbook.setId(1L);
        cashbook.setName("test name");
        Income firstIncome = new Income(1L, "flow purpose test", BigDecimal.valueOf(500), new Date(),
                "from test", true, cashbook);
        Income secondIncome = new Income(2L, "flow purpose test 2", BigDecimal.valueOf(410), new Date(),
                "from test 2", false, cashbook);
        List<Income> incomeList = new ArrayList<>();
        incomeList.add(firstIncome);
        incomeList.add(secondIncome);
        cashbook.setIncome(incomeList);
        Costs firstCost = new Costs(1L, "flow purpose test 3", BigDecimal.valueOf(800), new Date(),
                "from test 3", true, cashbook);
        Costs secondCost = new Costs(2L, "flow purpose test 4", BigDecimal.valueOf(360), new Date(),
                "from test 4", true, cashbook);
        List<Costs> costsList = new ArrayList<>();
        costsList.add(firstCost);
        costsList.add(secondCost);
        cashbook.setCosts(costsList);
    }

    @Test
    void countBalance() {
        Cashbook expectedCashbookBalance = new Cashbook();
        expectedCashbookBalance.setBalance(BigDecimal.valueOf(-250));
        CashbookBalanceCounter.countBalance(cashbook);
        assertEquals(expectedCashbookBalance.getBalance(), cashbook.getBalance());
    }
}
