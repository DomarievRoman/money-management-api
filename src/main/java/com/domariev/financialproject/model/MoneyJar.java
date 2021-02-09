package com.domariev.financialproject.model;

import com.domariev.financialproject.serializer.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "money_jar")
public class MoneyJar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "jar_name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_money_jar")
    private List<Contribution> jarBalance;
    @Column(name = "goal")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal goal;
}
