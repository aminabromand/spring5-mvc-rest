package com.example.springmvcrestdemo.bootstrap;

import com.example.springmvcrestdemo.domain.Category;
import com.example.springmvcrestdemo.domain.Customer;
import com.example.springmvcrestdemo.repositories.CategoryRepository;
import com.example.springmvcrestdemo.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
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
}
