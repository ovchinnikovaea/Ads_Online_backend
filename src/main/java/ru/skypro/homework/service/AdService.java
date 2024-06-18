package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;

/**
 * Интерфейс сервиса для управления объявлениями.
 */
public interface AdService {

    /**
     * Создает новое объявление.
     *
     * @param properties свойства создаваемого объявления
     * @param image файл изображения, связанный с объявлением
     * @param authentication информация об аутентификации пользователя, создающего объявление
     * @return созданное объявление в виде AdDto
     */
    AdDto createAd(CreateOrUpdateAdDto properties, MultipartFile image, Authentication authentication);

    ExtendedAdDto getExtendedAdDto(Integer id);

    AdsDto getAdsDtoMe(Authentication authentication);

    AdsDto getAllAds();

    ResponseEntity<Void> deleteAd(Integer id);

    AdDto updateAd(Integer id, CreateOrUpdateAdDto body);

    byte[] updateImage(Integer id, MultipartFile image);
}