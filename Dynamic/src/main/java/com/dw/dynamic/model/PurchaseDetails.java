package com.dw.dynamic.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "purchase_details")
public class PurchaseDetails {
    @Id
    @Column(name = "id")
    private String id;

    @OneToMany(mappedBy = "review")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "payment")
    private Payment payment;

}
