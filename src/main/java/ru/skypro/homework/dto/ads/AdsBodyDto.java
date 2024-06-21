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

    @JsonProperty("properties")
    private CreateOrUpdateAdDto properties = null;

    @JsonProperty("images")
    private Resource images = null;

    public AdsBodyDto properties (CreateOrUpdateAdDto properties) {
        this.properties = properties;
        return this;
    }

    public AdsBodyDto image(Resource images) {
        this.images = images;
        return this;
    }
}
