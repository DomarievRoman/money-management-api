package com.domariev.financialproject.model;

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
    private BigDecimal amounting;
    @Column(name = "comment")
    private String comment;
}
