package com.currency.Backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User extends BaseEntity implements Serializable {
    @Column(nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;
}
