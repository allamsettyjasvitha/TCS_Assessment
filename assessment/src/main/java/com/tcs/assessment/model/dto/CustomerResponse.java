package com.tcs.assessment.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CustomerResponse {

    private UUID id;
    private String name;
    private String email;
    private Double annualSpend;
    private LocalDate lastPurchaseDate;

}
