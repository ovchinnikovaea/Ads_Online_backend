package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.UploadImageService;

import javax.validation.Valid;

@RestController
@RequestMapping("/images")
@Tag(name = "загрузка изображения")
public class UploadImageController {
    private final UploadImageService uploadImageService;

    public UploadImageController(UploadImageService uploadImageService) {
        this.uploadImageService = uploadImageService;
    }

    @PostMapping(name = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "загрузка")
    public String uploadImage(@Parameter(description = "Файл изображения для загрузки", required = true, content = @Content(mediaType = "multipart/form-data"))
                                  @Valid @RequestPart(value = "image", required = false) MultipartFile image) {
        return uploadImageService.upload(image);
    }


}
