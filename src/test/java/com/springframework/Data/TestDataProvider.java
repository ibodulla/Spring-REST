package com.springframework.Data;

import com.springframework.domain.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Ibodulla Ibodullaev created on 3/18/2020 inside the package - com.springframework.Data
 */
public class TestDataProvider {

    public static Customer createCustomer() {
        return GenericBuilder.of(Customer::new)
                .with(Customer::setId, 1L)
                .with(Customer::setFirstname, "Ibodulla")
                .with(Customer::setFirstname, "Ibodullaev")
                .build();
    }

    public static List<Customer> createCustomerList() {
        List<Customer> customerList = new ArrayList<>();

        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstname("Brad");
        c1.setLastname("Pitt");
        customerList.add(c1);

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setFirstname("Leonardo");
        c2.setLastname("Dicaprio");
        customerList.add(c2);

        Customer c3 = new Customer();
        c3.setId(3L);
        c3.setFirstname("Ibodulla");
        c3.setLastname("Ibodullaev");
        customerList.add(c3);

        return customerList;
    }

}