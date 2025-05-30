package com.tcs.assessment.Model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CustomerRequest {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    private Double annualSpend;
    private LocalDate lastPurchaseDate;

    public String getName() {
      return name;
    }

    public String getEmail() {
        return email;
    }

    public Double getAnnualSpend() {
        return annualSpend;
    }

    public LocalDate getLastPurchaseDate() {
        return lastPurchaseDate;
    }
}