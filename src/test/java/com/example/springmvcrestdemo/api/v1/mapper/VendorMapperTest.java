package com.example.springmvcrestdemo.api.v1.mapper;

import com.example.springmvcrestdemo.api.v1.model.VendorDTO;
import com.example.springmvcrestdemo.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;
    public static final String NAME = "Joe";
    public static final long ID = 1L;

    @Test
    public void vendorToVendorDTO() throws Exception {

        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        //when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        //Then
        assertEquals(Long.valueOf(ID), vendorDTO.getId());
        assertEquals(NAME, vendorDTO.getName());
    }

}