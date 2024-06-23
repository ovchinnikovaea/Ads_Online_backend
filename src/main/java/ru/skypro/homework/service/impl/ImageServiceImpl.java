package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public ImageServiceImpl(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    @Override

    public Image addImage(MultipartFile multipartFile) {
        try {
            Image image = new Image();
            image.setImage(multipartFile.getBytes());
            return imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }

    @Override
    public byte[] getImageData(Integer id) {
        return imageRepository.findById(id)
                .map(Image::getImage)
                .orElseThrow(() -> new ImageNotFoundException("Image not found with id:"));
    }

    @Override

    public String getUserImageUrl(Integer id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Изображение не найдено"));
        if (image.getImage() != null) {
            return "/image/" + image.getId();
        } else {
            throw new ImageNotFoundException("Изображение не найдено");
        }
    }
}
