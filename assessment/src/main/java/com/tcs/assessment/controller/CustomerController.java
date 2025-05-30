package com.tcs.assessment.controller;

import com.tcs.assessment.model.dto.CustomerRequest;
import com.tcs.assessment.model.dto.CustomerResponse;
import com.tcs.assessment.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService svc;
    public CustomerController(CustomerService svc) {
        this.svc = svc;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<CustomerResponse> create(
            @RequestBody CustomerRequest req) {
        return ResponseEntity.status(CREATED).body(svc.createCustomer(req));
    }

    @GetMapping("/{id}")
    @Operation(
            summary     = "Retrieve a customer by ID"
    )
    public CustomerResponse getById(@PathVariable UUID id) {
        return svc.getCustomerById(id);
    }

    @GetMapping
    public CustomerResponse getByParam(
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String email) {
        if (name!=null) return svc.getCustomerByName(name);
        if (email!=null) return svc.getCustomerByEmail(email);
        throw new IllegalArgumentException("Provide name or email");
    }

    @PutMapping("/{id}")
    public CustomerResponse update(
            @PathVariable UUID id, @Valid @RequestBody CustomerRequest req) {
        return svc.updateCustomer(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        svc.deleteCustomer(id);
    }
}

