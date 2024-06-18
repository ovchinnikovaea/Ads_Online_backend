package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    @Override
    public AdDto createAd(CreateOrUpdateAdDto properties, MultipartFile image, Authentication authentication) {
        throw new RuntimeException("Метод не реализован");
    }

    @Override
    public ExtendedAdDto getExtendedAdDto(Integer id) {
        throw new RuntimeException("Метод не реализован");
    }

    @Override
    public AdsDto getAdsDtoMe(Authentication authentication) {
        throw new RuntimeException("Метод не реализован");
    }

    @Override
    public AdsDto getAllAds() {
        throw new RuntimeException("Метод не реализован");
    }

    @Override
    public ResponseEntity<Void> deleteAd(Integer id) {
        throw new RuntimeException("Метод не реализован");
    }

    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto body) {
        throw new RuntimeException("Метод не реализован");
    }

    @Override
    public byte[] updateImage(Integer id, MultipartFile image) {
        // обратится к репозиторию и получить объяву, если нет то бросить исключение Объява не найдена
        Ad ad = adRepository.findById(id)
                .orElseThrow(()-> new AdsNotFoundException(id));

        // загрузка изображения

        return new byte[0];
    }
}
