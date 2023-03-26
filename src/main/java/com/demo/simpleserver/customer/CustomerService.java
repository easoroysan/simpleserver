package com.demo.simpleserver.customer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer addNewCustomer(Customer customer) {
        if (customer.getFirstName() == null || customer.getFirstName().length() == 0) {
            throw new IllegalStateException("First name is required");
        }
        if (customer.getEmail() == null || customer.getEmail().length() == 0) {
            throw new IllegalStateException("Email is required");
        }
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomer(Long customerId, String firstName, String lastName, String email) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("customer with id " + customerId + " does not exist."));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(customer.getFirstName(), firstName)) {
            customer.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(customer.getLastName(), lastName)) {
            customer.setLastName(lastName);
        }
        if (email != null && email.length() > 0 && !Objects.equals(customer.getEmail(), email)) {
            customer.setEmail(email);
        }

        return customer;
    }

    public String deleteCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new IllegalStateException("customer with id " + customerId + " does not exist.");
        }
        customerRepository.deleteById(customerId);
        return "Customer has been deleted";
    }

}
