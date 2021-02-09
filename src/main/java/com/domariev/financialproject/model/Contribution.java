package com.domariev.financialproject.model;

import com.domariev.financialproject.serializer.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "contribution")
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amounting")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal amounting;
    @Column(name = "comment")
    private String comment;
}
