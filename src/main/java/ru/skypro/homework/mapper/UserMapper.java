package ru.skypro.homework.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.User.UpdateUserDTO;
import ru.skypro.homework.dto.User.UserDTO;
import ru.skypro.homework.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toDto(User user);
    User fromDto(UserDTO userDto);
    @IterableMapping(qualifiedByName = "toDto")
    List<UserDTO> toDtos(List<User> users);
    User UpdateUserDTO(UpdateUserDTO updateUserDTO);
}
