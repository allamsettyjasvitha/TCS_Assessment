package com.tcs.assessment.Service;

import com.tcs.assessment.Model.dto.CustomerRequest;
import com.tcs.assessment.Model.dto.CustomerResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest req);
    CustomerResponse getCustomerById(UUID id);
    CustomerResponse getCustomerByName(String name);
    CustomerResponse getCustomerByEmail(String email);
    CustomerResponse updateCustomer(UUID id, CustomerRequest req);
    void deleteCustomer(UUID id);
}
