package com.domariev.financialproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashbookStatistics {

    private BigDecimal largestIncomePayment;

    private BigDecimal largestCostPayment;

    private List<Income> allIncomesByMonth;

    private List<Costs> allCostsByMonth;

    private Cashbook cashbook;
}
