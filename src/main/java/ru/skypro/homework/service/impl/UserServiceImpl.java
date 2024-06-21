package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.authentication.RegisterDto;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.RegisterMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends UserNotFoundException implements UserService {
    private final AuthorityServiceImpl authorityService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageRepository imageRepository;
    private final PasswordEncoder encoder;
    private final RegisterMapper registerMapper;

    @Override
    public UserDto getUserDto(String userName) {
        return userMapper.userToUserDto(getUser(userName));
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public RegisterDto registerUser(RegisterDto body) {
        User user = new User();
        user.setUsername(body.getUsername());
        user.setPassword(encoder.encode(body.getPassword()));
        user.setFirstName(body.getFirstName());
        user.setLastName(body.getLastName());
        user.setPhone(body.getPhone());
        user.setRole(body.getRole());

        user.setEnabled(true);

        User savedUser = userRepository.save(user);
        authorityService.addAuthorities(user);
        return registerMapper.userToUserDto(savedUser);
    }

    @Override
    public byte[] getUserImage(int id) throws UserNotFoundException {
        return userRepository.findById(id).map(User::getImage).map(Image::getData).orElse(null);
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto body, Authentication authentication) throws UserNotFoundException {
        User user = getUser(authentication.getName());
        User infoToUpdate = userMapper.updateUserDtoToUser(body);

        user.setFirstName(infoToUpdate.getFirstName());
        user.setLastName(infoToUpdate.getLastName());
        user.setPhone(infoToUpdate.getPhone());
        return userMapper.updateUserToUserDto(userRepository.save(user));
    }

    @Override
    public void updateNewPassword(NewPasswordDto body, Authentication authentication) throws UserNotFoundException {
        User user = getUser(authentication.getName());
        User infoToUpdate = userMapper.updateNewPasswordDtoToUser(body);

        user.setPassword(encoder.encode(infoToUpdate.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void uploadImage(Authentication authentication, MultipartFile file) throws IOException {

        User users = userRepository.findByUsername(authentication.getName()).get();

        Image image = Optional.ofNullable(users.getImage()).orElseGet(Image::new);
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());
        imageRepository.save(image);

        users.setImage(image);
        userRepository.save(users);
    }

    @Override
    public byte[] getImage(Integer id) {
        return userRepository.findById(id).map(User::getImage).map(Image::getData).orElse(null);
    }
}