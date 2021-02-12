package com.domariev.financialproject.dto;

import com.domariev.financialproject.serializer.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;

@Data
public class MoneyJarDto {

    @Null
    private Long id;
    @NotBlank(message = "Money jar name can not be blank")
    private String name;
    private String description;
    @Null(message = "Redundant value for parameter balance of jar")
    private List<ContributionDto> jarBalance;
    @NotNull(message = "Goal can not be null")
    @DecimalMin(value = "1", message = "Invalid value of goal")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal goal;
    private Boolean achievedGoal = false;
}
