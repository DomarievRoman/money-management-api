package com.domariev.financialproject.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@EqualsAndHashCode(callSuper = true)
public class CostsDto extends MoneyFlowDto{

    @Null
    private Long id;

    @NotBlank(message = "Transaction recipient can not be blank ")
    private String to;

    private Boolean fullPaid = false;
}
