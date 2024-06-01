package ru.skypro.homework.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;

@CrossOrigin(value = "http://localhost:3000")
@RestController
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
    @PostMapping(value = "/ads")
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
    @GetMapping(value = "/ads/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(adService.getExtendedAdDto(id));
    }

    /**
     * Метод для получения объявлений авторизованного пользователя
     * @param authentication
     * @return
     */
    @GetMapping(value = "/ads/me")
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsDtoMe(authentication.getName()));
    }

    /**
     * Метод для получения всех объявлений
     * @return
     */
    @GetMapping(value = "/ads")
    public ResponseEntity<AdsDto> getAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }



}
