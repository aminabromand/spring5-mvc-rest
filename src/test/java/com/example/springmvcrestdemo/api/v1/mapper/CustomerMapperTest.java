package com.example.springmvcrestdemo.api.v1.mapper;

import com.example.springmvcrestdemo.api.v1.model.CustomerDTO;
import com.example.springmvcrestdemo.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    public static final String NAME = "Joe";
    public static final long ID = 1L;

    @Test
    public void customerToCustomerDTO() throws Exception {

        //given
        Customer customer = new Customer();
        customer.setFirstname(NAME);
        customer.setId(ID);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //Then
        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(NAME, customerDTO.getFirstname());
    }

}