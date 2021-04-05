package com.domariev.financialproject.model.abstractFlow;

import com.domariev.financialproject.serializer.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Date;

@MappedSuperclass
@Data
public class MoneyFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flowPurpose;
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal payment;
    private Date transactionDate;
}
