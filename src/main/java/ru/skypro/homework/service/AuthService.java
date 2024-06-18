package ru.skypro.homework.service;

import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.autoAndReg.Register;/*изначально эта зависимость была на файл в папке dto*/
import ru.skypro.homework.entity.User;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);

    void addAuthorities(User user, Role role);
}
