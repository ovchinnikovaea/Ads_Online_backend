package ru.skypro.homework.dto.comments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentDto {
    // Dto для описания комментария к объявлению
    @JsonProperty("author") // автор объявления
    private Integer author;
    @JsonProperty("authorImage") // аватар автора
    private String authorImage;
    @JsonProperty("authorFirstName") // имя автора
    private String authorFirstName;
    @JsonProperty("createdAt") // дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
    private Integer createdAt;
    @JsonProperty("pk") // Id
    private Integer pk;
    @JsonProperty("text") // Текст комментария
    private String text;
}
