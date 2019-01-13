package com.example.springmvcrestdemo.services;

import com.example.springmvcrestdemo.api.v1.mapper.VendorMapper;
import com.example.springmvcrestdemo.api.v1.model.VendorDTO;
import com.example.springmvcrestdemo.controllers.v1.VendorController;
import com.example.springmvcrestdemo.domain.Vendor;
import com.example.springmvcrestdemo.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
//        return vendorMapper.vendorToVendorDTO(vendorRepository.findById(id).get());

        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        return saveAndReturnDTO(vendor);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);
        returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));
        return returnDto;
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if(vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }
            VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
            returnDTO.setVendorUrl(getVendorUrl(id));
            return returnDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    private String getVendorUrl(Long id){
        return VendorController.BASE_URL + "/" + id;
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
