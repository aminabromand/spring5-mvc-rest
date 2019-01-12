package com.example.springmvcrestdemo.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {
    private Long id;

    @ApiModelProperty(value = "this is the vendor name", required = true)
    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;
}
