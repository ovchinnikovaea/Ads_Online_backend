package ru.skypro.homework.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/ads")
public class AdsController {

    /*
    Сервис и интерфейсы будут созданы на этапе 3
    В настоящий момент в контроллере используются предположительные названия методов будущего сервиса
     */
    private final AdService adService;
    private final CommentService commentService;

    @Autowired
    public AdsController(AdService adService, CommentService commentService) {
        this.adService = adService;
        this.commentService = commentService;
    }

    /**
     * Метод для создания объявления
     * @param properties
     * @param image
     * @param authentication
     * @return
     */
    @PostMapping
    public ResponseEntity<AdDto> addAd(@RequestPart(value = "properties", required = false) CreateOrUpdateAdDto properties,
                                       @Valid @RequestPart(value = "image", required = false) MultipartFile image,
                                       Authentication authentication) {
        return new ResponseEntity<>(adService.createAd(properties, image, authentication), HttpStatus.CREATED);
    }

    /**
     * Метод для получения информации об объявлении
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(adService.getExtendedAdDto(id));
    }

    /**
     * Метод для получения объявлений авторизованного пользователя
     * @param authentication
     * @return
     */
    @GetMapping(value = "/me")
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsDtoMe(authentication.getName()));
    }

    /**
     * Метод для получения всех объявлений
     * @return
     */
    @GetMapping
    public ResponseEntity<AdsDto> getAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    /**
     * Метод для удаления объявления по id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") Integer id) {
        adService.deleteAd(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Метод для обновления информации об объявлении
     * @param id
     * @param body
     * @return
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable("id") Integer id,
                                           @Valid @RequestBody CreateOrUpdateAdDto body) {
        return ResponseEntity.ok(adService.updateAd(id, body));
    }

    /**
     * Метод для обновления картинки для объявления
     * @param id
     * @param image
     * @return
     */
    @PatchMapping(value = "/{id}/image")
    public ResponseEntity<byte[]> updateImage(@PathVariable("id") Integer id,
                                              @Valid @RequestPart(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.ok(adService.updateImage(id, image));
    }
}
