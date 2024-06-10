package ru.skypro.homework.mapper;
/*маппер для логина*/

import ru.skypro.homework.dto.autoAndReg.Login;

public class LoginMapper {
    public Login mapstruct () {
        Login register = new Login();
        return Login;
    }

}
