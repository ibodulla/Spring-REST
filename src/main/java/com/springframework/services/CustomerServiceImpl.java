package com.springframework.services;

import com.springframework.domain.Customer;
import com.springframework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Ibodulla Ibodullaev created on 3/13/2020 inside the package - com.springframework.services
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomerById(long id) {
        return customerRepository.getOne(id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}