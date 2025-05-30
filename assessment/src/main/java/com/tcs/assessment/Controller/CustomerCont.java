package com.tcs.assessment.Controller;

import com.tcs.assessment.Model.dto.CustomerRequest;
import com.tcs.assessment.Model.dto.CustomerResponse;
import com.tcs.assessment.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService svc;
    public CustomerController(CustomerService svc) { this.svc = svc; }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerRequest req) {
        return ResponseEntity.status(CREATED).body(svc.createCustomer(req));
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable UUID id) {
        return svc.getCustomerById(id);
    }

    @GetMapping
    public CustomerResponse getByParam(
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String email) {
        if (name!=null) return svc.getCustomerByName(name);
        if (email!=null) return svc.getCustomerByEmail(email);
        throw new BadRequestException("Provide name or email");
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

