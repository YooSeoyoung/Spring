package com.dw.dynamic.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "payment_name")
    private String paymentName;

    @ManyToOne
    @JoinColumn(name = "cart")
    private Cart cart;
}
