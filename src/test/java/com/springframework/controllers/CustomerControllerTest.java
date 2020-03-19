package com.springframework.controllers;

import com.springframework.Data.TestDataProvider;
import com.springframework.SpringMvcRestApplication;
import com.springframework.domain.Customer;
import com.springframework.services.CustomerService;
import com.springframework.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import javax.transaction.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Ibodulla Ibodullaev created on 3/18/2020 inside the package - com.springframework.services
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SpringMvcRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class CustomerControllerTest {

    public static final String BASE_URL = "/api/v1/customers/";

    private MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Autowired
    private CustomerController customerController;

    @Before
    public void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

    }

    @Test
    public void getCustomer() throws Exception {
        Customer customer = TestDataProvider.createCustomer();

        when(customerService.findCustomerById(any(Long.class))).thenReturn(customer);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL +  new Long(1)).accept(APPLICATION_JSON_UTF8))
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        Customer customer1 = TestUtil.jsonToObject(result.getResponse().getContentAsString(), Customer.class);
        assertNotNull(customer1);
//        System.out.println(customer1.getId() + " " + customer1.getFirstname() + " " + customer1.getLastname());
        assertEquals(1L, customer1.getId());
    }

    @Test
    public void getCustomer1() throws Exception {
        Customer customer = TestDataProvider.createCustomer();
        when(customerService.findCustomerById(any(Long.class))).thenReturn(customer);
        MvcResult result = mockMvc.perform(get(BASE_URL + +  new Long(1))
                .contentType(APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(customer)))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.firstname", is(equalTo(customer.getFirstname()))))
                .andReturn();
    }

    @Test
    public void saveCustomer() throws Exception {
        Customer customer = TestDataProvider.createCustomer();
        when(customerService.saveCustomer(any())).thenReturn(customer);
        mockMvc.perform(post(BASE_URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(customer)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstname", is(equalTo(customer.getFirstname()))))
                .andReturn()
        ;
    }

}