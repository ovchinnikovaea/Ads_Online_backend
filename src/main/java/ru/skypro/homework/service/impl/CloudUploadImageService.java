package ru.skypro.homework.service.impl;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.UploadImageService;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class CloudUploadImageService implements UploadImageService {
    @Override
    public String upload(MultipartFile image) {

        Dotenv dotenv = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;



        Map<String, Boolean> params1 = Map.of(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        Map<String, Object> result;
        try {
            byte[] byteArray = image.getBytes();
            result = cloudinary.uploader().upload(byteArray, params1);
        } catch (IOException e) {
            log.error("Ошибка при загрузке изображений!");
            throw new RuntimeException(e);
        }

        log.error("Изображение успешно загрузилось!");
        return (String) result.get("secure_url");
    }
}
