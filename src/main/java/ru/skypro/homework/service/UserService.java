package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.User.NewPasswordDTO;
import ru.skypro.homework.dto.User.UpdateUserDTO;
import ru.skypro.homework.dto.User.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;

public interface UserService {
    UserDTO getUserDto(String userName);

    User getUser(String username) throws UserNotFoundException;

    void registerUser(User user, Role role);

    byte[] getUserImage(int id) throws UserNotFoundException;

    UpdateUserDTO updateUser(UpdateUserDTO body, Authentication authentication) throws UserNotFoundException;

    void updateNewPassword(NewPasswordDTO body, Authentication authentication) throws UserNotFoundException;

    void uploadImage(MultipartFile image, Authentication authentication) throws UserNotFoundException;
}
