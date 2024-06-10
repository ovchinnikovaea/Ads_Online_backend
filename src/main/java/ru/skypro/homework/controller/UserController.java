package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.User.NewPasswordDTO;
import ru.skypro.homework.dto.User.UpdateUserDTO;
import ru.skypro.homework.dto.User.UserDTO;

import java.awt.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private RoleService roleService;
    private Mapper mapper;

    public UserController(UserService userService, RoleService roleService, Mapper mapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.mapper = mapper;
    }
    @PostMapping
    public NewPasswordDTO  set_password(){}
    @GetMapping
    public UserDTO me(){}
    @PatchMapping
    private UpdateUserDTO me(){}
    @PatchMapping("/me")
    private Image image(){}
}
