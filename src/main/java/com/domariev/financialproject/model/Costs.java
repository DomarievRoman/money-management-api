package com.domariev.financialproject.model;

import com.domariev.financialproject.model.abstractFlow.MoneyFlow;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_costs")
public class Costs extends MoneyFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "costs_to")
    private String to;
    @Column(name = "full_paid_costs")
    @JsonProperty
    private Boolean fullPaid = false;

    public Boolean getFullPaid(){
        return fullPaid;
    }
}
