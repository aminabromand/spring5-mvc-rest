package com.example.springmvcrestdemo.repositories;

import com.example.springmvcrestdemo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
