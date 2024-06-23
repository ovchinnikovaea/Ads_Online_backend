package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.UserNotFoundException;

public interface ImageService {

    Image addImage(MultipartFile multipartFile);

    byte[] getImageData(Integer id);

    String getUserImageUrl(Integer id);

}
