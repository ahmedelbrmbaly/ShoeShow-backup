package iti.jets.model.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class UserManageDTO {
    private Long userId;
    private String name;
    private String phoneNumber;
    private String email;
    private String job;
    private Date birthdate;

    public UserManageDTO() {
    }

    public UserManageDTO(Long id, String name, String phoneNumber, String email, String job, Date birthdate) {
        this.userId = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.job = job;
        this.birthdate = birthdate;
    }
}
