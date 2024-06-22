package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

public interface ImageService {
    Image addImage(MultipartFile imageMultipart);

    Image getImage(Integer id);

    byte[] getImageData(Integer id);

    String getAdImageUrl(int adId);

    String getUserImageUrl(int userId);
}
