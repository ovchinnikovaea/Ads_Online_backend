package ru.skypro.homework.mapper;
/*маппер для регистрациию*/
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.autoAndReg.Register;

@Component
public class RegisterMapper {
    public Register mapstruct () {
        Register register = new Register();
        return Register;
    }
}
