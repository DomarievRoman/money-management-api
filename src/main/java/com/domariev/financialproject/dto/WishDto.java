package com.domariev.financialproject.dto;

import com.domariev.financialproject.serializer.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class WishDto {

    @Null
    private Long id;
    @NotBlank(message = "Wish name can not be blank")
    private String name;
    @NotNull(message = "Price can not be null")
    @DecimalMin(value = "1", message = "Invalid price")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal price;
    @NotNull(message = "Date to buy can not be null")
    private LocalDate dateToBuy;
    private Boolean implemented = false;

}
