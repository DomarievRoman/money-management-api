package com.domariev.financialproject.model;

import com.domariev.financialproject.model.abstractFlow.MoneyFlow;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_costs")
public class Costs extends MoneyFlow {
    @Column(name = "costs_to")
    private String to;
    @Column(name = "full_paid_costs")
    private Boolean fullPaid = true;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cashbook cashbook;

    public Costs() {
    }

    public Costs(Long id, String flowPurpose, BigDecimal payment, Date transactionDate, String to, Boolean fullPaid, Cashbook cashbook) {
        super(id, flowPurpose, payment, transactionDate);
        this.to = to;
        this.fullPaid = fullPaid;
        this.cashbook = cashbook;
    }

    public Boolean getFullPaid() {
        return fullPaid;
    }
}
