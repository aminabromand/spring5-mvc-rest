package com.example.springmvcrestdemo.bootstrap;

import com.example.springmvcrestdemo.domain.Category;
import com.example.springmvcrestdemo.domain.Customer;
import com.example.springmvcrestdemo.domain.Vendor;
import com.example.springmvcrestdemo.repositories.CategoryRepository;
import com.example.springmvcrestdemo.repositories.CustomerRepository;
import com.example.springmvcrestdemo.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories Loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer sam = new Customer();
        sam.setFirstname("Sam");
        sam.setLastname("Jackson");

        Customer thomson = new Customer();
        thomson.setFirstname("Thomson");
        thomson.setLastname("Cotura");

        Customer willy = new Customer();
        willy.setFirstname("Willy");
        willy.setLastname("Joseph");



        customerRepository.save(sam);
        customerRepository.save(thomson);
        customerRepository.save(willy);

        System.out.println("Customers Loaded = " + customerRepository.count());
    }

    private void loadVendors() {
        Vendor sam = new Vendor();
        sam.setName("Peter");

        Vendor thomson = new Vendor();
        thomson.setName("Valerie");

        Vendor willy = new Vendor();
        willy.setName("Josephine");

        vendorRepository.save(sam);
        vendorRepository.save(thomson);
        vendorRepository.save(willy);

        System.out.println("Vendors Loaded = " + vendorRepository.count());
    }
}
