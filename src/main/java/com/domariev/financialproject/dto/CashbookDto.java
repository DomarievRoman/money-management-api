package com.domariev.financialproject.dto;

import com.domariev.financialproject.serializer.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

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
    private List<IncomeDto> income;

    @Null(message = "Redundant value for parameter costs")
    private List<CostsDto> costs;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal balance = BigDecimal.ZERO;
}
