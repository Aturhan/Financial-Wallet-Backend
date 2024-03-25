package com.currency.Backend.model;

import com.currency.Backend.model.constants.SpendType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "spending")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Spending extends BaseEntity implements Serializable {
    private String title;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private SpendType spendType;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
