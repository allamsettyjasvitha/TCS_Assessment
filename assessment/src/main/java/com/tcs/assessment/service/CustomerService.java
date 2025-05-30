package com.tcs.assessment.service;

import com.tcs.assessment.model.dto.CustomerRequest;
import com.tcs.assessment.model.dto.CustomerResponse;

import java.util.UUID;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest req);
    CustomerResponse getCustomerById(UUID id);
    CustomerResponse getCustomerByName(String name);
    CustomerResponse getCustomerByEmail(String email);
    CustomerResponse updateCustomer(UUID id, CustomerRequest req);
    void deleteCustomer(UUID id);
}
