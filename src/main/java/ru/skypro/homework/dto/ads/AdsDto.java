package ru.skypro.homework.dto.ads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdsDto {

    // Dto для списка объявлений
    // где results - коллекция объявлений в формате AdDto
    @JsonProperty("count") // общее количество объявлений
    private Integer count;

    @JsonProperty("results") // коллекция объявлений
    @Valid
    private Collection<AdDto> results;
}
