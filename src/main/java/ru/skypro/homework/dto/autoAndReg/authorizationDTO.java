package ru.skypro.homework.dto.autoAndReg;

/* Здесь должна быть хорошая авторизация. И будет. Наверно.*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class authorizationDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;


}
