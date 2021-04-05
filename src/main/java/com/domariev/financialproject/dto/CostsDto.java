package com.domariev.financialproject.dto;

import com.domariev.financialproject.model.Cashbook;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Null;

@Data
@EqualsAndHashCode(callSuper = true)
public class CostsDto extends MoneyFlowDto{

    @Null
    private Long id;

    private String to;

    private Boolean fullPaid = true;

    private Cashbook cashbook;
}
