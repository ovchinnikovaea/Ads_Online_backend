package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;

public interface AdService {
    AdsDto getAllAds();
    void createAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, Authentication authentication);
    ExtendedAdDto getExtendedAdDto(Integer id);
    AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto);
    AdsDto getAdsDtoMe(String username);
    void deleteAd(Integer idPk);
    Ad getAdById(Integer id);
    void updateImage(int id, MultipartFile image);
}
