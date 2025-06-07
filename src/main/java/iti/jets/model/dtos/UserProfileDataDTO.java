package iti.jets.model.dtos;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class UserProfileDataDTO {
    private Long userId;

    private String name;

    private String phoneNumber;

    private String email;

    private Date birthdate;

    private String job;

    private BigDecimal creditLimit;

    private String interests;

    private List<UserAddressDTO> addresses;

    private List<OrderDTO> orders;
}