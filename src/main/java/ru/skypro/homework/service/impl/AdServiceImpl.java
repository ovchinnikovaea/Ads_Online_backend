package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdMapper adMapper;

    @Override
    public AdsDto getAllAds() {
        return adMapper.getAds(adRepository.findAll());
    }

    @Override
    public void createAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        Ad ad = adMapper.createAdsDtoToAds(createOrUpdateAdDto);
        ad.setAuthor(user);
        Ad savedAd = adRepository.save(ad);
        if (image != null && !image.isEmpty()) {
            Image adsImage = imageService.addImage(image);
            ad.setImage(adsImage);
            adRepository.save(ad);
        }
    }

    @Override
    public ExtendedAdDto getExtendedAdDto(Integer idPk) {
        return adMapper.adToExtendedAdDto(getAdById(idPk));
    }

    @Override
    public AdDto updateAd(Integer idPk, CreateOrUpdateAdDto createOrUpdateAdDto) {
        Ad oldAds = getAdById(idPk);
        Ad infoToUpdate = adMapper.createAdsDtoToAds(createOrUpdateAdDto);

        oldAds.setPrice(infoToUpdate.getPrice());
        oldAds.setTitle(infoToUpdate.getTitle());
        oldAds.setDescription(infoToUpdate.getDescription());

        Ad updatedAds = adRepository.save(oldAds);
        return adMapper.adToAdDto(updatedAds);
    }

    @Override
    public AdsDto getAdsDtoMe(String userName) {
        return adMapper.getAds(adRepository.findByAuthorId(userService.getUser(userName).getId()).orElse(new ArrayList<>()));
    }

    @Override
    public void deleteAd(Integer idPk) {
        adRepository.delete(getAdById(idPk));
    }

    @Override
    public void updateImage(int id, MultipartFile imageMultipart) {
        Ad ad = getAdById(id);
        Image image = imageService.addImage(imageMultipart);
        ad.setImage(image);
        adRepository.save(ad);
    }

    @Override
    public Ad getAdById(Integer id) {
        return adRepository.findById(id).orElseThrow(AdNotFoundException::new);
    }

}


