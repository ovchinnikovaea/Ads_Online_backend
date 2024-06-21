package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.service.UserService;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin//(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/set_password")
    public ResponseEntity<?> setPassword(@Valid @RequestBody NewPasswordDto body,
                                         Authentication authentication) {
        userService.updateNewPassword(body, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/me")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserDto(authentication.getName()));
    }

    @PatchMapping(value = "/me")
    public ResponseEntity<UpdateUserDto> updateUser(@Valid @RequestBody UpdateUserDto body,
                                                       Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(body, authentication));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(Authentication authentication, @RequestParam MultipartFile image) throws IOException {
        userService.uploadImage(authentication, image);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/image")
    public ResponseEntity<byte[]> getUserImage(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserImage(id));
    }
}
