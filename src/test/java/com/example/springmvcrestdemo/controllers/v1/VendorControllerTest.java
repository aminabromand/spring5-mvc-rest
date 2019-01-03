package com.example.springmvcrestdemo.controllers.v1;

import com.example.springmvcrestdemo.api.v1.model.VendorDTO;
import com.example.springmvcrestdemo.controllers.RestResponseEntityExceptionHandler;
import com.example.springmvcrestdemo.services.VendorService;
import com.example.springmvcrestdemo.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest extends AbstractRestControllerTest {

    public static final String FIRSTNAME1 = "Jim";
    public static final long ID1 = 1L;
    public static final String FIRSTNAME2 = "Bob";
    public static final long ID2 = 2L;

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListVendors() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setId(ID1);
        vendor1.setName(FIRSTNAME1);

        VendorDTO vendor2 = new VendorDTO();
        vendor2.setId(ID2);
        vendor2.setName(FIRSTNAME2);

        List<VendorDTO> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get(VendorController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(vendors.size())));
    }

    @Test
    public void testGetById() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setId(ID1);
        vendor1.setName(FIRSTNAME1);

        when(vendorService.getVendorById(anyLong())).thenReturn(vendor1);

        mockMvc.perform(get(VendorController.BASE_URL + "/" + ID1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(FIRSTNAME1)));
    }


    @Test
    public void createNewVendor() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Fred");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL + "/1");

        when(vendorService.createNewVendor(vendor)).thenReturn(returnDTO);

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.name",equalTo("Fred")))
                .andExpect(jsonPath("$.vendor_url",equalTo(VendorController.BASE_URL + "/1")));
    }

    @Test
    public void testUpdateVendor() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Fred");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL + "/1");

        when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Fred")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));
    }

    @Test
    public void testPatchVendor() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Fred");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL + "/1");

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Fred")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));
    }

    @Test
    public void testDeleteVendor() throws Exception {
        mockMvc.perform(delete(VendorController.BASE_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(vendorService).deleteVendorById(anyLong());
    }

    @Test
    public void testNotFound() throws Exception {
        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get(CategoryController.BASE_URL + "/69")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}