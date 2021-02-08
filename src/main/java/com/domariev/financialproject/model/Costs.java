package com.domariev.financialproject.model;

import com.domariev.financialproject.model.abstractFlow.MoneyFlow;
import lombok.Data;

import javax.persistence.*;

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
    private Boolean isFullPaid;
}
