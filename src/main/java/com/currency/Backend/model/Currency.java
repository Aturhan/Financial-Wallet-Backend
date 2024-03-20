package com.currency.Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
@Entity
@Table(name = "currency")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Currency extends BaseEntity implements Serializable {
    private String code;
    private String currencyDescription;
    private String buyingPrice;
    private String sellingPrice;
}
