package ru.skypro.homework.dto.comments;

import lombok.Data;

import java.util.List;
@Data
public class CommentsDTO {

    private Integer count;

    private List<CommentDTO> results;
}
