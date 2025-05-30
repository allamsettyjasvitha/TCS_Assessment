package com.tcs.assessment.Model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private Double annualSpend;

    private LocalDate lastPurchaseDate;

    public Customer() {}

    public Customer(String name, String email, Double annualSpend, LocalDate lastPurchaseDate) {
        this.name = name;
        this.email = email;
        this.annualSpend = annualSpend;
        this.lastPurchaseDate = lastPurchaseDate;
    }

    @PrePersist
    public void assignId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    public void setName(String name) {
    }

    public void setEmail(String email) {
    }

    public void setAnnualSpend(Double annualSpend) {
    }

    public void setLastPurchaseDate(LocalDate lastPurchaseDate) {
    }

    public double getAnnualSpend() {

        return annualSpend != null ? annualSpend : 0.0;
    }

    public LocalDate getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    public Object getId() {
        return id;
    }

    public Object getName() {
        return name;
    }

    public Object getEmail() {
        return email;
    }


    // getters and setters omitted for brevity
}
