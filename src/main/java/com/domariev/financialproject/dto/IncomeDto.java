package com.domariev.financialproject.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@EqualsAndHashCode(callSuper = true)
public class IncomeDto extends MoneyFlowDto {

    @Null
    private Long id;

    @NotBlank(message = "Transaction sender can not be blank")
    private String from;

    private Boolean regular = false;
}

