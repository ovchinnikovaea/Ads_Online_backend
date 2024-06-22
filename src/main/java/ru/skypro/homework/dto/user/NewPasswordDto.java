package ru.skypro.homework.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordDto {

    @JsonProperty("currentPassword") // текущий пароль
    private String currentPassword;

    @JsonProperty("newPassword") // новый пароль
    private String newPassword;
}
