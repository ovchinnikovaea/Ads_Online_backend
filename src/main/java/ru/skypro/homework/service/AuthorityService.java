package ru.skypro.homework.service;

import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;

public interface AuthorityService {
    void addAuthorities(User user, Role role);

    String getAuthorities(User user);
}