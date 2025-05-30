package com.tcs.assessment.model;


import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private Double annualSpend;

    private LocalDate lastPurchaseDate;

    public Customer() {}




    public Customer(String name, String email, Double annualSpend, LocalDate lastPurchaseDate) {
        // assignId();
        this.name = name;
        this.email = email;
        this.annualSpend = annualSpend;
        this.lastPurchaseDate = lastPurchaseDate;
    }

    public void assignId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }







    // getters and setters omitted for brevity
}
