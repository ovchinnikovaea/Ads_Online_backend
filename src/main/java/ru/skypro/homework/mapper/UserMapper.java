package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AuthorityRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.impl.AuthServiceImpl;

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
    AuthorityRepository authorityRepository;

    @Mapping(target = "image", expression = "java(imageService.getUserImageUrl(user.getId()))")
    @Mapping(target = "role", expression = "java(Role.valueOf(authorityService.getAuthorities(user)))", ignore = true)
    public abstract UserDto userToUserDto(User user);

    public abstract User updateUserDtoToUser(UpdateUserDto updateUserDto);

    public abstract UpdateUserDto updateUserToUserDto(User user);
    @Mapping(target = "password",source = "newPassword")
    public abstract User updateNewPasswordDtoToUser(NewPasswordDto newPasswordDto);
}
