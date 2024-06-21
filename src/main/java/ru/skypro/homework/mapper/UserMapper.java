package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;

@Component
@Mapper(componentModel = "spring", imports = {Role.class})
public interface UserMapper {

    @Mapping(target = "image", expression = "java(user.getImage() != null ? \"/avatars/\"+user.getId() : null)")
    public abstract UserDto userToUserDto(User user);

    public abstract User updateUserDtoToUser(UpdateUserDto updateUserDto);

    public abstract UpdateUserDto updateUserToUserDto(User user);

    @Mapping(target = "password", source = "newPassword")
    public abstract User updateNewPasswordDtoToUser(NewPasswordDto newPasswordDto);
}
