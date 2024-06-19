package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.service.UserService;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/set_password")
    public ResponseEntity<?> setPassword(@Valid @RequestBody NewPasswordDto body, Authentication authentication) {
        userService.updateNewPassword(body, authentication);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/users/me")
    public ResponseEntity<UpdateUserDto> UpdateUserDto(@Valid @RequestBody UpdateUserDto body, Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(body, authentication));
    }

    @PatchMapping(value = "/users/me/image")
    public ResponseEntity<Void> updateUserImage(@Valid @RequestPart(value = "image", required = false) MultipartFile image, Authentication authentication) {
        userService.uploadImage(image, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/users/{id}/image")
    public ResponseEntity<byte[]> getUserImage(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserImage(id));
    }
}
