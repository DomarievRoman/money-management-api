package com.domariev.financialproject.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "cash_book")
public class Cashbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cash_book")
    private List<Income> income;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cash_book")
    private List<Costs> costs;
    @Column(name = "balance")
    private BigDecimal balance;
}
