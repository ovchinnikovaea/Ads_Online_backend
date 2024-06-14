package ru.skypro.homework.service;

import ru.skypro.homework.dto.autoAndReg.Register;

public interface AuthoritiesService {
    boolean login (String userName, String password);
    boolean register (Register register);
}
