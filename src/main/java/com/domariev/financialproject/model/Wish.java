package com.domariev.financialproject.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "wish")
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "wish_name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "date_to_buy")
    private LocalDate dateToBuy;
    @Column(name = "implemeted_wish")
    private Boolean isImplemented;
}
