package com.springframework.bootstrap;

import com.springframework.domain.Customer;
import com.springframework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Ibodulla Ibodullaev created on 3/13/2020 inside the package - com.springframework.bootstrap
 */
@Component
public class BootStrapData implements CommandLineRunner{

    private final CustomerRepository customerRepository;

    public BootStrapData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        System.out.println("Loading Customer Data");

        Customer c1 = new Customer();
        c1.setFirstname("Brad");
        c1.setLastname("Pitt");
        customerRepository.save(c1);

        Customer c2 = new Customer();
        c1.setFirstname("Leonardo");
        c1.setLastname("Dicaprio");
        customerRepository.save(c2);

        Customer c3 = new Customer();
        c1.setFirstname("Tony");
        c1.setLastname("Stark");
        customerRepository.save(c3);

        System.out.println("Customers Saved: " + customerRepository.count());
    }
}