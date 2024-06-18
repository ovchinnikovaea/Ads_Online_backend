package ru.skypro.homework.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

public interface ImageService {
    byte[] getImageData(Long id);

    Image addImage(MultipartFile imageMultipart);
}
