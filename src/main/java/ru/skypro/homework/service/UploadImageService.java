package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageService {
    /**
     * @param image файл изображения
     * @return ссылка на изображение для просмотра
     */
    String upload(MultipartFile image);
}
