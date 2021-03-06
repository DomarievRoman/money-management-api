package com.domariev.financialproject.model;

import com.domariev.financialproject.model.abstractFlow.MoneyFlow;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_income")
public class Income extends MoneyFlow {
    @Column(name = "income_from")
    private String from;
    @Column(name = "regular_income")
    private Boolean regular = false;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cashbook cashbook;

    public Income() {
    }

    public Income(Long id, String flowPurpose, BigDecimal payment, Date transactionDate, String from, Boolean regular, Cashbook cashbook) {
        super(id, flowPurpose, payment, transactionDate);
        this.from = from;
        this.regular = regular;
        this.cashbook = cashbook;
    }

    public Boolean getRegular() {
        return regular;
    }
}
