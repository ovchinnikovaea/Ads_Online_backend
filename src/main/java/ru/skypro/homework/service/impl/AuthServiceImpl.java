package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.security.MyUserDetailsService;
import ru.skypro.homework.service.AuthService;

import javax.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = passwordEncoder;
    }

    @Override
    @Transactional
    public boolean login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            return false; // Пользователь не найден
        }
        return encoder.matches(password, userDetails.getPassword());
    }


}
