package com.domariev.financialproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MoneyFlowDto {
    private Long id;
    @NotBlank(message = "Transaction purpose can not be blank")
    private String flowPurpose;
    @NotNull(message = "Payment amount cannot be null")
    private BigDecimal payment;
    private LocalDateTime transactionDate;
}
