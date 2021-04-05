package com.domariev.financialproject.model;

import com.domariev.financialproject.model.abstractFlow.MoneyFlow;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_income")
public class Income extends MoneyFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "income_from")
    private String from;
    @Column(name = "regular_income")
    private Boolean regular = false;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cashbook cashbook;

    public Boolean getRegular() {
        return regular;
    }
}
