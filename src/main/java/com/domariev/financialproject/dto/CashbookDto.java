package com.domariev.financialproject.dto;

import com.domariev.financialproject.model.Costs;
import com.domariev.financialproject.model.Income;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CashbookDto {

    @Null
    private Long id;

    @NotBlank(message = "Cashbook name can not be blank")
    private String name;

    @Null(message = "Redundant value for parameter income")
    private List<Income> income;

    @Null(message = "Redundant value for parameter costs")
    private List<Costs> costs;

    private BigDecimal balance = BigDecimal.ZERO;
}
