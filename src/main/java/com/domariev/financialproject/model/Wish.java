package com.domariev.financialproject.model;

import com.domariev.financialproject.serializer.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal price;
    @Column(name = "date_to_buy")
    private LocalDate dateToBuy;
    @Column(name = "implemeted_wish")
    private Boolean isImplemented;
}
