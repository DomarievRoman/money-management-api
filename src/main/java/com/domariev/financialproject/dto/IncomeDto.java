package com.domariev.financialproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class IncomeDto extends MoneyFlowDto{

    @Null
    private Long id;

    @NotBlank(message = "Transaction sender can not be blank")
    private String from;

    private Boolean regular;
}
