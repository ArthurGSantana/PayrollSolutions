package com.project.payrollSolutions.dto;


import com.project.payrollSolutions.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDTO {
    private Long id;
    private String streetAddress;
    private String city;
    private String postalCode;
    private String neighborhood;
    private String houseNumber;

    public Address transformToAddress() {
        return new Address(this.streetAddress, this.city, this.postalCode, this.neighborhood, this.houseNumber);
    }
}