package ru.skypro.homework.dto.authentication;

/* Здесь должна быть хорошая авторизация. И будет. Наверно.*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
    // Dto для авторизации пользователя

    @JsonProperty("username") // имя пользователя
    private String username;

    @JsonProperty("password") // пароль
    @Size(min=8,max=16) // требование к длине
    private String password;
}
