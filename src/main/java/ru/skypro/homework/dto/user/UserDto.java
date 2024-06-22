package ru.skypro.homework.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.skypro.homework.entity.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDto {
    @JsonProperty("id") // Id
    private Integer id;

    @JsonProperty("email") // Адрес эл.почты
    private String email;

    @JsonProperty("firstName") // имя
    private String firstName;

    @JsonProperty("lastName") // фамилия
    private String lastName;

    @JsonProperty("phone") // номер телефона
    private String phone;

    @JsonProperty("role") // привилегии (ADMIN, USER)
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonProperty("image") // изображение
    private String image;

}
