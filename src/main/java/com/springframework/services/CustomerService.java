package com.springframework.services;

import com.springframework.domain.Customer;

import java.util.List;

/**
 * Ibodulla Ibodullaev created on 3/13/2020 inside the package - com.springframework.services
 */
public interface CustomerService {

    Customer findCustomerById(long id);

    List<Customer> findAllCustomers();

    Customer saveCustomer(Customer customer);
}