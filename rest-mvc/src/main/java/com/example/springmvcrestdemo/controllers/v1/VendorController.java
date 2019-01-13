package com.example.springmvcrestdemo.controllers.v1;

import com.example.springmvcrestdemo.api.v1.model.VendorDTO;
import com.example.springmvcrestdemo.api.v1.model.VendorListDTO;
import com.example.springmvcrestdemo.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is my Vendor Controller")
@Controller
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "This will get a list of vendors.", notes = "These are some notes about the API.")
    @GetMapping
    public ResponseEntity<VendorListDTO> getAllVendors() {
        return new ResponseEntity<VendorListDTO>(
                new VendorListDTO(vendorService.getAllVendors()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable Long id) {
        return new ResponseEntity<VendorDTO>(
                vendorService.getVendorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VendorDTO> createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<VendorDTO>(vendorService.createNewVendor(vendorDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<VendorDTO>(vendorService.saveVendorByDTO(id, vendorDTO),
                HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VendorDTO> patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<VendorDTO>(vendorService.patchVendor(id, vendorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
