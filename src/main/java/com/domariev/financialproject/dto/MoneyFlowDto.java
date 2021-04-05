package com.domariev.financialproject.dto;

import com.domariev.financialproject.serializer.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MoneyFlowDto {
    @Null
    private Long id;
    @NotBlank(message = "Transaction purpose can not be blank")
    private String flowPurpose;
    @NotNull(message = "Payment amount cannot be null")
    @JsonSerialize(using = MoneySerializer.class)
    @DecimalMin(value = "1", message = "Invalid payment")
    private BigDecimal payment;
    @NotNull(message = "Transaction date cannot be null")
    private Date transactionDate;
}
