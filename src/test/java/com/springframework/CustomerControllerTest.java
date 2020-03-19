package com.springframework;

import com.springframework.Data.TestDataProvider;
import com.springframework.controllers.CustomerController;
import com.springframework.domain.Customer;
import com.springframework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Ibodulla Ibodullaev created on 3/18/2020 inside the package - com.springframework.controllers
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = SpringMvcRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class CustomerControllerTest  {

    private static final long CUSTOMER_ID = 1;

    @Autowired
    private MockMvc mvc;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private CustomerController customerController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(customerController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void testGetAllCustomers() {

    }

    @Test
    public void getCustomerById() throws Exception{
        Customer customer = TestDataProvider.createCustomer();
        mvc.perform(get("/api/v1/customers/1")
                .contentType(APPLICATION_JSON))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.firstname", is(equalTo(customer.getFirstname()))))
                .andReturn();

    }

    public void testSaveCustomer() {

    }
}
