package com.springframework.services;

import com.springframework.domain.Customer;
import com.springframework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static com.springframework.Data.TestDataProvider.*;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Ibodulla Ibodullaev created on 3/18/2020 inside the package - com.springframework.services
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void findCustomerById() throws NullPointerException{
        Customer customer1 = createCustomer();
        when(customerRepository.getOne(1L)).thenReturn((customer1));
        assertEquals(customer1.getFirstname(), (customerService.findCustomerById(customer1.getId()).getFirstname()));
    }

    @Test
    public void findAllCustomers() {
        when(customerRepository.findAll()).thenReturn(createCustomerList());
        assertEquals(3, customerService.findAllCustomers().size());
    }

    @Test
    public void saveCustomer() {
        Customer customerNew = createCustomer();

        when(customerService.saveCustomer(any())).thenReturn(customerNew);
        Customer customer1 = customerService.saveCustomer(customerNew);
        when(customerRepository.getOne(1L)).thenReturn((customerNew));
        assertEquals(customer1, customerService.findCustomerById(customer1.getId()));

    }

}