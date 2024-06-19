package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.awt.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository repository;
    @Autowired
    private UserService userService;

    private UserMapper mapper;

    public UserController(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @PostMapping
    public NewPasswordDto set_password(){
        return null;
    }
    @GetMapping
    public UserDto me(){
        return null;
    }
//    @PatchMapping
//    private UpdateUserDto me(){
//        return null;
//    }
    @PatchMapping("/me")
    private Image image(){
        return null;
    }
}
