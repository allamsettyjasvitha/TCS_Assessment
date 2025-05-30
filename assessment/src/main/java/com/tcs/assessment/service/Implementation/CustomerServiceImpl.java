package com.tcs.assessment.service.Implementation;
import com.tcs.assessment.service.CustomerService;
import com.tcs.assessment.model.dto.CustomerRequest;
import com.tcs.assessment.model.dto.CustomerResponse;
import com.tcs.assessment.exception.CustomerNotFoundException;
import com.tcs.assessment.model.Customer;
import com.tcs.assessment.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

@Service

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }


    public CustomerResponse createCustomer(CustomerRequest req) {
        Customer c = new Customer(req.getName(),
                req.getEmail(),
                req.getAnnualSpend(),
                req.getLastPurchaseDate());
        Customer saved = repo.save(c);
        return toResponse(saved);
    }


    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(UUID id) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return toResponse(c);
    }


    @Transactional(readOnly = true)
    public CustomerResponse getCustomerByName(String name) {
        Customer c = repo.findByName(name)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return toResponse(c);
    }


    @Transactional(readOnly = true)
    public CustomerResponse getCustomerByEmail(String email) {
        Customer c = repo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return toResponse(c);
    }


    public CustomerResponse updateCustomer(UUID id, CustomerRequest req) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        c.setName(req.getName());
        c.setEmail(req.getEmail());
        c.setAnnualSpend(req.getAnnualSpend());
        c.setLastPurchaseDate(req.getLastPurchaseDate());
        return toResponse(repo.save(c));
    }


    public void deleteCustomer(UUID id) {
        if (!repo.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found");
        }
        repo.deleteById(id);
    }

    private CustomerResponse toResponse(Customer c) {

        return CustomerResponse.builder().id(c.getId()).name(c.getName()).annualSpend(c.getAnnualSpend()).email(c.getEmail()).build();
    }

    private String calculateTier(Customer c) {
        double spend = (Objects.isNull(c.getAnnualSpend()) ? 0.0 : c.getAnnualSpend());
        LocalDate last = c.getLastPurchaseDate();
        LocalDate now = LocalDate.now();
        if (spend >= 10000 && last != null && Period.between(last, now).toTotalMonths() <= 6) {
            return "Platinum";
        }
        if (spend >= 1000 && last != null && Period.between(last, now).toTotalMonths() <= 12) {
            return "Gold";
        }
        return "Silver";
    }
}



