package com.demo.simpleserver.customer;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/customers")
public class CustumerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public Customer registerNewCustomer(@RequestBody Customer newCustomer) {
        return customerService.addNewCustomer(newCustomer);
    }

    @PutMapping(path = "{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
        return customerService.updateCustomer(customerId, firstName, lastName, email);
    }

    @DeleteMapping(path = "{customerId}")
    public String deleteCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.deleteCustomer(customerId);
    }
}
