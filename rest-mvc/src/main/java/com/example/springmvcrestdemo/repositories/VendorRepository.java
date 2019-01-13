package com.example.springmvcrestdemo.repositories;

import com.example.springmvcrestdemo.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByName(String name);
}
