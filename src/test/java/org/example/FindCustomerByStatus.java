package org.example;

import org.example.Model.Customer;
import org.example.Repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FindCustomerByStatus {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindCustomerByStatus() {
        // Given
        CustomerStatus status = CustomerStatus.Gold;
        Customer customer = new Customer("Test Customer");
        customer.setCustomerStatus(status); // Set the status directly
        customer = customerRepository.save(customer);

        // When
        List<Customer> foundCustomers = customerRepository.findByCustomerStatus(status);

        // Then
        assertThat(foundCustomers).isNotEmpty();
        assertThat(foundCustomers.get(0).getCustomerStatus()).isEqualTo(status);
    }
}
