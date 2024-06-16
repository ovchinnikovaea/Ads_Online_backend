package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.User.NewPasswordDTO;
import ru.skypro.homework.dto.User.UpdateUserDTO;
import ru.skypro.homework.dto.User.UserDTO;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Slf4j
@Service
public class UserServiceImpl extends UserNotFoundException implements UserService {
    private final AuthServiceImpl authoritiesService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final PasswordEncoder encoder;

    public UserServiceImpl(AuthServiceImpl authoritiesService, UserRepository userRepository,
                           UserMapper userMapper, PasswordEncoder encoder) {
        this.authoritiesService = authoritiesService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Override
    public UserDTO getUserDto(String userName) {
        return userMapper.userToUserDto(getUser(userName));
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void registerUser(User user, Role role) {
        user.setEnabled(true);
        authoritiesService.addAuthorities(user, role);
        userRepository.save(user);
    }

    @Override
    public byte[] getUserImage(int id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (user.getImage() == null) {
            throw new ImageNotFoundException();
        }

        return imageService.getImageData(user.getImage().getId());
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO body, Authentication authentication) throws UserNotFoundException {
        User user = getUser(authentication.getName());
        User infoToUpdate = userMapper.updateUserDtoToUser(body);

        user.setFirstName(infoToUpdate.getFirstName());
        user.setLastName(infoToUpdate.getLastName());
        user.setPhone(infoToUpdate.getPhone());
        return userMapper.updateUserToUserDto(userRepository.save(user));
    }

    @Override
    public void updateNewPassword(NewPasswordDTO body, Authentication authentication) throws UserNotFoundException {
        User user = getUser(authentication.getName());
        User infoToUpdate = userMapper.updateNewPasswordDtoToUser(body);

        user.setPassword(encoder.encode(infoToUpdate.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void uploadImage(MultipartFile imageMultipart, Authentication authentication) throws UserNotFoundException {
        User user = getUser(authentication.getName());

        Image image = imageService.addImage(imageMultipart);
        user.setImage(image);

        userRepository.save(user);
    }
}