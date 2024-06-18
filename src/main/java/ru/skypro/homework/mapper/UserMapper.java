package ru.skypro.homework.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.User.NewPasswordDTO;
import ru.skypro.homework.dto.User.UpdateUserDTO;
import ru.skypro.homework.dto.User.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.LoginRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;
import ru.skypro.homework.service.impl.ImageService;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        imports = {Role.class})
public abstract class UserMapper {
    @Autowired
    ImageService imageService;
    @Autowired
    AuthServiceImpl authorityService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;
//    @Mapping(target = "image", expression = "java(imageService.getUserImageUrl(user.getId()))")
//    @Mapping(target = "role", expression = "java(Role.valueOf(authorityService.getAuthorities(user)))")
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "role", ignore = true)
    public abstract UserDTO userToUserDto(User user);

    public abstract User updateUserDtoToUser(UpdateUserDTO updateUserDto);

    public abstract UpdateUserDTO updateUserToUserDto(User user);
    @Mapping(target = "password",source = "newPassword")
    public abstract User updateNewPasswordDtoToUser(NewPasswordDTO newPasswordDto);
}
