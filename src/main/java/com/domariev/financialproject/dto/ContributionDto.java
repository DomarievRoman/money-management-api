package com.domariev.financialproject.dto;

import com.domariev.financialproject.serializer.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
public class ContributionDto {

    @Null
    private Long id;
    @NotNull(message = "Amounting of contribution can not be null")
    @DecimalMin(value = "1", message = "Invalid amounting")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal amounting;
    @Column(name = "comment")
    private String comment;
}
