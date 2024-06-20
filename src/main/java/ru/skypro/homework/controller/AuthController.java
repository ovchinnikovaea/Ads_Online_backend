package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.authentication.LoginDto;/*изначально эта зависимость была на файл в папке dto*/
import ru.skypro.homework.dto.authentication.RegisterDto;/*изначально эта зависимость была на файл в папке dto*/
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin//(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDTO) {
        if (authService.login(loginDTO.getUsername(), loginDTO.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDto> register(@Valid @RequestBody RegisterDto body) {
        //authService.register(body);
        return new ResponseEntity<>(userService.registerUser(body), HttpStatus.CREATED);
//        if (authService.register(body)) {
//            return ResponseEntity.status(HttpStatus.CREATED).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        public ResponseEntity<AdDto> addAd(@RequestPart(value = "properties", required = false) CreateOrUpdateAdDto properties,
//                @Valid @RequestPart(value = "image", required = false) MultipartFile image,
//                Authentication authentication) {
//            return new ResponseEntity<>(adService.createAd(properties, image, authentication), HttpStatus.CREATED);
//        }


//        User user = new User();
//        user.setUsername(registerDto.getUsername());
//        user.setPassword(registerDto.getPassword());
//        user.setFirstName(registerDto.getFirstName());
//        user.setLastName(registerDto.getLastName());
//        user.setPhone(registerDto.getPhone());
//        user.setRole(registerDto.getRole());
//        userService.registerUser(user, registerDto.getRole());
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
