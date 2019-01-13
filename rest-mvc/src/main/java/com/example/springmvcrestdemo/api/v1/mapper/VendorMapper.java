package com.example.springmvcrestdemo.api.v1.mapper;

import com.example.springmvcrestdemo.api.v1.model.VendorDTO;
import com.example.springmvcrestdemo.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDtoToVendor(VendorDTO vendorDTO);
}
