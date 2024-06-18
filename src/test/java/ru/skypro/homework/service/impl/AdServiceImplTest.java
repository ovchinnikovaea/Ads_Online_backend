package ru.skypro.homework.service.impl;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.repository.AdRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AdServiceImplTest {

    AdRepository adRepository = mock(AdRepository.class);

    AdServiceImpl adService = new AdServiceImpl(adRepository);

    @Test
    void whenAdDoesNotExist_thenThrowNotFoundException() {
        //given
        when(adRepository.findById(anyInt()))
                .thenReturn(Optional.empty());
        Integer id = 3245233;

        //when
        AdsNotFoundException exception = assertThrows(AdsNotFoundException.class,
                ()-> adService.updateImage(id, new MockMultipartFile("новая картина", new byte[0])));

        //then
        assertEquals("Объявление c id=3245233 не найдено", exception.getMessage());
    }

    @Test
    void whenAdDExists_thenReturnEmptyArray() {
        //given
        Integer id = 3453;
        when(adRepository.findById(id))
                .thenReturn(Optional.of(new Ad()));

        //when
        byte[] array = adService
                .updateImage(id, new MockMultipartFile("новая картина", new byte[0]));

        assertArrayEquals(new byte[0], array);

    }
}