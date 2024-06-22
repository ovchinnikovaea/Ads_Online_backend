package ru.skypro.homework.dto.ads;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdsBodyDto {

    @JsonProperty("properties") // title, price, description
    private CreateOrUpdateAdDto properties = null;

    @JsonProperty("images") // ссылка на картинку объявления
    private Resource images = null;
}
