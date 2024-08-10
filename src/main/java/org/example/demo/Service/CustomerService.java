package org.example.demo.Service;

import org.example.demo.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    Customer getCustomerById(Long id);
    void deleteCustomerById(Long id);
    Optional<Customer> findByCustomerNumber(String customerNumber);
    Customer updateCustomer(Long id, Customer customer);
}
