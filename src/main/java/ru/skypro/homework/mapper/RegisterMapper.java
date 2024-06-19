package ru.skypro.homework.mapper;
/*маппер для регистрациию*/
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.authentication.RegisterDto;
import ru.skypro.homework.entity.User;

@Component
@Mapper(componentModel = "spring")
public abstract class RegisterMapper {
    public abstract RegisterDto toDto(User user);

    @Mapping(target = "email", source = "username")
    public abstract User toEntity(RegisterDto userDto);
}
