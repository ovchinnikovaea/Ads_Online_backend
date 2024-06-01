package ru.skypro.homework.dto.User;

import lombok.Data;

@Data
public class NewPasswordDTO {
    private String currentPassword;
    private String newPassword;
}
