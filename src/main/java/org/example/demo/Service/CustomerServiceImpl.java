package org.example.demo.Service;

import org.example.demo.entities.Customer;
import org.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findByCustomerNumber(String customerNumber) {
        return customerRepository.findByCustomerNumber(customerNumber);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        existingCustomer.setCustomerNumber(customer.getCustomerNumber());
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setInitialDeposit(customer.getInitialDeposit());
        existingCustomer.setYears(customer.getYears());
        existingCustomer.setSavingsType(customer.getSavingsType());
        return customerRepository.save(existingCustomer);
    }
}
