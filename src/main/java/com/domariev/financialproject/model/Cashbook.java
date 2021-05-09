package com.domariev.financialproject.model;

import com.domariev.financialproject.serializer.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cash_book")
public class Cashbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cashbook_id", updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Income> income;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cashbook_id", updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private List<Costs> costs;
    @Column(name = "balance")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal balance = BigDecimal.ZERO;
}
