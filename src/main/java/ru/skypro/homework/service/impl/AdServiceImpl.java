package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Value("${image.dir.path}")
    private String imagesDir;

    private String objectAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    /**
     * Метод возвращает список всех объявлений в виде DTO {@link AdDto}.
     *
     * @return возвращает все объявления из БД
     */
    @Override
    public AdsDto getAllAds() {
        List<AdDto> collect = adRepository.findAll().stream()
                .map(adMapper::adToAdDto)
                .collect(Collectors.toList());
        return new AdsDto(collect.size(), collect);
    }

    /**
     * Метод добавляет новое объявление в БД
     *
     * @param createAd - DTO модель класса {@link CreateOrUpdateAdDto};
     * @param file     - фотография объявления
     * @return возвращает объявление в качестве DTO модели
     */
    @Override
    @Transactional
    public CreateOrUpdateAdDto createAd(Authentication authentication, CreateOrUpdateAdDto createAd, MultipartFile file) throws IOException {

        User user = userRepository.findByUsername(authentication.getName()).get();
        Image image = imageService.addImage(file);
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());

        Ad ad = new Ad();
        ad.setTitle(createAd.getTitle());
        ad.setPrice(createAd.getPrice());
        ad.setDescription(createAd.getDescription());
        ad.setAuthor(user);
        ad.setImages(image);
        adRepository.save(ad);
        return adMapper.updateAdToDto(ad);
    }

    /**
     * Метод изменяет объявление
     *
     * @param id  - id объявления
     * @param createOrUpdateAdDto - DTO модель класса {@link CreateOrUpdateAdDto};
     * @return возвращает DTO модель объявления
     */
    @Override
    public CreateOrUpdateAdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        Ad adEntity = adRepository.findById(id).get();
        adEntity.setTitle(createOrUpdateAdDto.getTitle());
        adEntity.setPrice(createOrUpdateAdDto.getPrice());
        adEntity.setDescription(createOrUpdateAdDto.getDescription());
        adRepository.save(adEntity);
        return adMapper.updateAdToDto(adEntity);
    }

    /**
     * Метод получает все объявления текущего пользователя
     *
     * @return возвращает DTO - список моделей объявления пользователя
     */
    @Override
    public AdsDto getAdsDtoMy(Authentication authentication) {
//        User user = userRepository.findByUsername(authentication.getName()).get();
//        List<AdDto> collect = user.getAdEntityList().stream().map(adMapper::adToAdDto).collect(Collectors.toList());
//        return new AdsDto(collect.size(),collect);
        return null;
    }

    /**
     * Метод удаляет объявление по id
     *
     * @param id - id объявления
     * @return boolean
     */
    @Override
    public void deleteAd(Integer id) {
        adRepository.deleteById(id);
    }

    @Override
    public void updateImage(Integer id, MultipartFile file) throws IOException {

        Ad ad = findAd(id);

        Image image = ad.getImages();
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());
        imageRepository.save(image);

        ad.setImages(image);
        adRepository.save(ad);
    }

    /**
     * Метод получает информацию об объявлении по id
     *
     * @param id - id объявления
     * @return возвращает DTO модель объявления
     */
    @Override
    public ExtendedAdDto getAdById(Integer id) {
        return adRepository.findById(id).map(adMapper::adToExtendedAdDto).orElse(null);
    }

    public Optional<Ad> findAdById(Integer id) {
        return adRepository.findById(id);
    }
    public Ad findAd(Integer id) {
        return adRepository.findById(id).get();
    }

    @Override
    public byte[] getImage(Integer id) {
        return adRepository.findById(id).map(Ad::getImages).map(Image::getData).orElse(null);
    }
}


