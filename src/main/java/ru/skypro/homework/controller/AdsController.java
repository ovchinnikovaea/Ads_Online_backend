package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {

    /*
    Сервис и интерфейсы будут созданы на этапе 3
    В настоящий момент в контроллере используются предположительные названия методов будущего сервиса
     */
    private final AdService adService;

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
        return ResponseEntity.ok(adService.getAdsDtoMe(authentication));
    }

    /**
     * Метод для получения всех объявлений
     * @return список всех объявлений
     */
    @GetMapping
    public ResponseEntity<AdsDto> getAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    /**
     * Метод для удаления объявления по id
     * @param id идентификатор объявления
     * @return пустой ответ
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removedAd(@PathVariable("id") Integer id) {
        return adService.deleteAd(id);
    }

    /**
     * Метод для обновления информации об объявлении
     * @param id идентификатор объявления
     * @param body новое содержание объявления
     * @return обновление объявление
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable("id") Integer id,
                                           @Valid @RequestBody CreateOrUpdateAdDto body) {
        return ResponseEntity.ok(adService.updateAd(id, body));
    }

    /**
     * Метод для обновления картинки для объявления
     * @param id идентификатор объявления
     * @param image изображение товара или услуги
     * @return
     */
    @PatchMapping(value = "/{id}/image")
    public ResponseEntity<byte[]> updateImage(@PathVariable("id") Integer id,
                                              @Valid @RequestPart(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.ok(adService.updateImage(id, image));
    }
}
