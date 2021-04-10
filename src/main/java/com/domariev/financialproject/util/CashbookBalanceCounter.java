package com.domariev.financialproject.util;

import com.domariev.financialproject.model.Cashbook;
import com.domariev.financialproject.model.abstractFlow.MoneyFlow;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class CashbookBalanceCounter {

    public static void countBalance(Cashbook cashbook) {
        BigDecimal incomeSum = cashbook.getIncome().stream().map(MoneyFlow::getPayment).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal costsSum = cashbook.getCosts().stream().map(MoneyFlow::getPayment).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal currentBalance = incomeSum.subtract(costsSum);
        cashbook.setBalance(currentBalance);
    }
}
