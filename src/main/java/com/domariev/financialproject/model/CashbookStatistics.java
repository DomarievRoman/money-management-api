package com.domariev.financialproject.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CashbookStatistics {

    private BigDecimal largestIncomePayment;

    private BigDecimal largestCostPayment;

    private List<Income> allIncomesByMonth;

    private List<Costs> allCostsByMonth;

    private Cashbook cashbook;
}
