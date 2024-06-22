package ru.skypro.homework.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateUserDto {
    @JsonProperty("firstName") // имя
    private String firstName;

    @JsonProperty("lastName") // фамилия
    private String lastName;

    @JsonProperty("phone") // номер телефона
    private String phone;
}
