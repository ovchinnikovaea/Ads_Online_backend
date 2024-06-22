/* DTO Register - для регистрации. Напиши сюда если что то поменяется*/

package ru.skypro.homework.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.skypro.homework.entity.Role;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    // Dto для регистрации пользователя

    @JsonProperty("username") // имя пользователя
    private String username;

    @JsonProperty("password") // пароль
    private String password;

    @JsonProperty("firstName") // имя
    private String firstName;

    @JsonProperty("lastName") // фамилия
    private String lastName;

    @JsonProperty("phone") // номер телефона
    private String phone;

    @JsonProperty("role") // привилегии (ADMIN, USER)
    private Role role;

}
