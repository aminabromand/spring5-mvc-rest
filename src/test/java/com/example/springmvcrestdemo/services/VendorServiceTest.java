package com.example.springmvcrestdemo.services;

import com.example.springmvcrestdemo.api.v1.mapper.VendorMapper;
import com.example.springmvcrestdemo.api.v1.model.VendorDTO;
import com.example.springmvcrestdemo.domain.Vendor;
import com.example.springmvcrestdemo.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class VendorServiceTest {

    public static final Long ID = 2L;
    public static final String FIRSTNAME = "Jimmy";
    public static final String LASTNAME = "Steward";
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getAllVendors() throws Exception {
        //given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        //then
        assertEquals(vendors.size(), vendorDTOS.size());
    }

    @Test
    public void getVendorById() throws Exception {
        //given
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(FIRSTNAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        //then

        assertEquals(ID, vendorDTO.getId());
        assertEquals(FIRSTNAME, vendorDTO.getName());
    }

    @Test
    public void createNewVendor() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Jim");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/vendors/1", savedDto.getVendorUrl());
    }

    @Test
    public void savedVendorByDTO() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Jim");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.saveVendorByDTO(1L, vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/vendors/1", savedDto.getVendorUrl());
    }

    @Test
    public void deleteVendorById() throws Exception {
        Long id = 1L;
        vendorRepository.deleteById(id);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }

}