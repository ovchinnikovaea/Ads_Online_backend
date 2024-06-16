package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.comments.CommentDTO;
import ru.skypro.homework.dto.comments.CommentsDTO;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDTO;

import java.util.List;

public interface CommentService {

    CommentsDTO getAllCommentsByAuthor(Integer adId);

    CommentDTO addCommentToAd(Integer adId, CreateOrUpdateCommentDTO commentDTO);

    void deleteComment(Integer adId, Integer commentId);

    CommentDTO updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO commentDTO);

}
