package com.tcs.assessment.service;


import com.tcs.assessment.repository.CustomerRepository;
import com.tcs.assessment.model.Customer;
import com.tcs.assessment.model.dto.CustomerRequest;
import com.tcs.assessment.model.dto.CustomerResponse;
import com.tcs.assessment.service.Implementation.CustomerServiceImpl;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
@Data

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repo;

    @InjectMocks
    private CustomerServiceImpl service;

    private CustomerRequest req;

    @BeforeEach
    void setUp() {
        req = new CustomerRequest();
        req.setName("Alice");
        req.setEmail("alice@example.com");
        req.setAnnualSpend(1234.56);
        req.setLastPurchaseDate(LocalDate.of(2025, 5, 29));
    }

    @Test
    void createCustomer_shouldReturnResponseWithGeneratedId() {
        // Arrange: prepare a Customer with an ID
        Customer saved = new Customer();
        saved.setId(UUID.randomUUID());
        saved.setName(req.getName());
        saved.setEmail(req.getEmail());
        saved.setAnnualSpend(req.getAnnualSpend());
        saved.setLastPurchaseDate(req.getLastPurchaseDate());

        when(repo.save(any(Customer.class))).thenReturn(saved);

        // Act
        CustomerResponse resp = service.createCustomer(req);

        // Assert
        assertThat(resp.getId()).isEqualTo(saved.getId());
        assertThat(resp.getName()).isEqualTo("Alice");
        verify(repo, times(1)).save(any(Customer.class));
    }

    @Test
    void getCustomerById_found() {
        UUID id = UUID.randomUUID();
        Customer c = new Customer();
        c.setId(id);
        c.setName("Bob");
        when(repo.findById(id)).thenReturn(Optional.of(c));

        CustomerResponse resp = service.getCustomerById(id);
        assertThat(resp.getId()).isEqualTo(id);
        assertThat(resp.getName()).isEqualTo("Bob");
    }

    @Test
    void getCustomerById_notFound_shouldThrow() {
        UUID id = UUID.randomUUID();
        when(repo.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getCustomerById(id))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found");
    }
}

