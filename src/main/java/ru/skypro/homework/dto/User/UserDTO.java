package ru.skypro.homework.dto.User;

import lombok.Data;

import java.time.format.SignStyle;
@Data
public class UserDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private User role;
    private String image;

}
