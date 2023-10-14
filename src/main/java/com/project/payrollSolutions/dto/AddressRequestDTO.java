package com.project.payrollSolutions.dto;


import com.project.payrollSolutions.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDTO {
    @Null
    private Long id;

    @NotNull
    @NotBlank
    private String streetAddress;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String postalCode;

    @NotNull
    @NotBlank
    private String neighborhood;

    @NotNull
    @NotBlank
    private String houseNumber;

    public Address transformToAddress() {
        return new Address(this.streetAddress, this.city, this.postalCode, this.neighborhood, this.houseNumber);
    }
}