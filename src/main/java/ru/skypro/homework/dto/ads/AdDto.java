package ru.skypro.homework.dto.ads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {

    // Dto для объявления
    @JsonProperty("author") // id автора объявления
    private Integer author;

    @JsonProperty("images") // ссылка на картинку объявления
    private String images;

    @JsonProperty("pk") // id объявления
    private Integer pk;

    @JsonProperty("price") // цена объявления
    private BigDecimal price;

    @JsonProperty("title") // заголовок объявления
    private String title;
}
