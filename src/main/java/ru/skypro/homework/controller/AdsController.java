package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.service.AdService;
import java.io.IOException;
import javax.validation.Valid;

@Slf4j
@CrossOrigin//(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdService adService;

    /**
     * Метод для получения всех объявлений
     * @return
     */
    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        AdsDto ads = adService.getAllAds();
        return ResponseEntity.ok(ads);
    }

    /**
     * Метод для создания объявления
     * @param properties имеет формат CreateOrUpdateAdDto и включает поля: description, title, price
     * @param image изображение в формате MultipartFile
     * @param authentication проверка аутентификации авторизованного пользователя
     * @return возвращает сообщение в формате HttpStatus.CREATED
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateOrUpdateAdDto> addAd(Authentication authentication,
                                       @RequestPart(value = "properties", required = true) CreateOrUpdateAdDto properties,
                                       @RequestPart(value = "image", required = true) MultipartFile image) throws IOException {
        if (image == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
//        CreateOrUpdateAdDto createAd = adService.createAd(authentication, properties, image);
        return new ResponseEntity<>(adService.createAd(authentication, properties, image), HttpStatus.CREATED);
    }

    /**
     * Метод для получения информации об объявлении
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable("id") Integer id) {
        ExtendedAdDto extendedAd = adService.getAdById(id);
        if (extendedAd == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(extendedAd);
    }

    /**
     * Метод для удаления объявления по id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable("id") Integer id) {
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
    public ResponseEntity<CreateOrUpdateAdDto> updateAds(@PathVariable("id") Integer id,
                                           @Valid @RequestBody CreateOrUpdateAdDto body) {
        return ResponseEntity.ok(adService.updateAd(id, body));
    }

    /**
     * Метод для получения объявлений авторизованного пользователя
     * @param authentication
     * @return
     */
    @GetMapping(value = "/me")
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication) {
        AdsDto ads = adService.getAdsDtoMy(authentication);
        return ResponseEntity.ok(ads);
    }

    /**
     * Метод для обновления картинки для объявления
     * @param id
     * @param image
     * @return
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> updateImage(@PathVariable("id") Integer id,
                                          @RequestParam MultipartFile image) throws IOException {
        if (adService.findAdById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        adService.updateImage(id, image);
        return ResponseEntity.ok().build();
    }
}
