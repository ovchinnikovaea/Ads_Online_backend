/* DTO Register - для регистрации. Напиши сюда если что то поменяется*/

package ru.skypro.homework.dto.autoAndReg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.skypro.homework.dto.Role;

@Data
public class RegisterDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("role")
    private Role role;
}
