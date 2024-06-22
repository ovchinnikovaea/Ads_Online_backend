package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;

import java.io.IOException;
import java.util.Optional;

public interface AdService {
    AdsDto getAllAds();
    AdDto createAd(CreateOrUpdateAdDto createAd, MultipartFile file, Authentication authentication);
//    ResponseEntity<ExtendedAdDto> getExtendedAdDto(Integer id);
    CreateOrUpdateAdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto);

    AdsDto getAdsDtoMy(Authentication authentication);
    void deleteAd(Integer idPk);
    ExtendedAdDto getAdById(Integer id);
    void updateImage(Integer id, MultipartFile image) throws IOException;
    Optional<Ad> findAdById(Integer id);
    byte[] getImage (Integer id) throws IOException;
}
