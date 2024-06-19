package ru.skypro.homework.service;

import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;

public interface CommentService {

    CommentsDto getAllCommentsByAuthor(Integer adId);

    CommentDto addCommentToAd(Integer adId, CreateOrUpdateCommentDto commentDTO);

    void deleteComment(Integer adId, Integer commentId);

    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto commentDTO);

}
