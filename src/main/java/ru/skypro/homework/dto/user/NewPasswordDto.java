package ru.skypro.homework.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordDto {
    @JsonProperty("currentPassword")
    private String currentPassword;
    @JsonProperty("newPassword")
    private String newPassword;
}
