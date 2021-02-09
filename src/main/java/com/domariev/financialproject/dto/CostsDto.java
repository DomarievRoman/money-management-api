package com.domariev.financialproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class CostsDto extends MoneyFlowDto{

    @Null
    private Long id;

    @NotBlank(message = "Transaction recipient can not be blank ")
    private String to;

    private Boolean fullPaid;
}
