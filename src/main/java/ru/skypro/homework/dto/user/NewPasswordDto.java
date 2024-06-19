package ru.skypro.homework.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewPasswordDto {
    @JsonProperty("currentPassword")
    private String currentPassword;
    @JsonProperty("newPassword")
    private String newPassword;
}
