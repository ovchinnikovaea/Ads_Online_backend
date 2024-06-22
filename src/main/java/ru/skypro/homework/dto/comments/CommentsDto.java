package ru.skypro.homework.dto.comments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;
@Data
@AllArgsConstructor
public class CommentsDto {
    // Dto для списка комментариев

    @JsonProperty("count") // счетчик комментариев
    private Integer count;

    @JsonProperty("results") // коллекция комментариев в формате AdDto
    @Valid
    private List<CommentDto> results;

}
