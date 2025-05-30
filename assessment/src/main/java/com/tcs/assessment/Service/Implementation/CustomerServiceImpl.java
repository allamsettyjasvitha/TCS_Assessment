package com.tcs.assessment.Service.Implementation;
import com.tcs.assessment.Service.CustomerService;
import com.tcs.assessment.Model.dto.CustomerRequest;
import com.tcs.assessment.Model.dto.CustomerResponse;
import com.tcs.assessment.Exception.CustomerNotFoundException;
import com.tcs.assessment.Model.Customer;
import com.tcs.assessment.Repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
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
        return toResponse(repo.save(c));
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
        CustomerResponse r = new CustomerResponse();
        r.setId(c.getId());
        r.setName(c.getName());
        r.setEmail(c.getEmail());
        r.setAnnualSpend(c.getAnnualSpend());
        r.setLastPurchaseDate(c.getLastPurchaseDate());
        r.setTier(calculateTier(c));
        return r;
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



