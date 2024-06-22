package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.service.impl.ImageServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class AdMapper {

    @Autowired
    ImageServiceImpl imageService;
    public abstract Ad createAdsDtoToAds(CreateOrUpdateAdDto createOrUpdateAdDto);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", expression = "java(ad.getAuthor().getId())")
//    @Mapping(target = "price", source = "price")
//    @Mapping(target = "title", source = "title")
    @Mapping(target = "images", expression = "java(\"C:\\\\Users\\\\stron\\\\IdeaProjects\\\\Ads_Online_backend\\\\images\\\\\"+ad.getId())")
//    @Mapping(target = "users")
    public abstract AdDto adToAdDto(Ad ad);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", expression = "java(ad.getAuthor().getFirstName())")
    @Mapping(target = "authorLastName", expression = "java(ad.getAuthor().getLastName())")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "email", expression = "java(ad.getAuthor().getEmail())")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "phone", expression = "java(ad.getAuthor().getPhone())")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "images", expression = "java(\"C:\\\\Users\\\\stron\\\\IdeaProjects\\\\Ads_Online_backend\\\\images\\\\\"+ad.getId())")
    public abstract ExtendedAdDto adToExtendedAdDto(Ad ad);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    public abstract CreateOrUpdateAdDto updateAdToDto(Ad ad);
}
