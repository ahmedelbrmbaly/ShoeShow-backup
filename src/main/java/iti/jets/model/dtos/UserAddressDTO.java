package iti.jets.model.dtos;

import lombok.*;

@Data
@ToString
public class UserAddressDTO {
    private Long addressId;
    private Integer buildingNumber;
    private String street;
    private String state;
    private Boolean isDefault;
}
