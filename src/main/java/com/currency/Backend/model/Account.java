package com.currency.Backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity implements Serializable {
    private Double amount;
    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Spending> spendingList = new ArrayList<>();
    @OneToOne(mappedBy = "account",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private User user;
}
