package com.springframework.repositories;

import com.springframework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Ibodulla Ibodullaev created on 3/13/2020 inside the package - com.springframework.repositories
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}