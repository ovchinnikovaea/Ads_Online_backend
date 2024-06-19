/* DTO Register - для регистрации. Напиши сюда если что то поменяется*/

package ru.skypro.homework.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.skypro.homework.entity.Role;

@Data
public class RegisterDto {
    // Dto для регистрации пользователя

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

    public String getUsername() {
        return "";
    }

    public String getPassword() {
        return "";
    }

//    public <E extends Enum<E>> Enum<E> getRole() {
//        return null;
//    }
}
