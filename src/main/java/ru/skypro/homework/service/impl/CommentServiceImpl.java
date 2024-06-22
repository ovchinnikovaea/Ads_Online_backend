package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final AdRepository adRepository;

    private final CommentMapper commentMapper;

    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, CommentMapper commentMapper, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
    }

    /**
     * Метод получает список всех комментариев объявления
     *
     * @param id - id объявления
     * @return возвращает DTO - список моделей комментариев
     */
    @Override
    public CommentsDto getAllCommentsByAd(Integer id) {
//        List<Comment> comments = commentRepository.findByAdId(id);
//        return commentMapper.commentsToCommentsDTO(comments);
        List<Comment> comments = adRepository.findById(id).orElseThrow().getComments();
        List<CommentDto> collect = comments.stream().map(commentMapper::commentToCommentDTO).collect(Collectors.toList());
        return new CommentsDto(collect.size(), collect);
    }

    /**
     * Метод добавляет комментарий к объявлению
     *
     * @param adId                  - id объявления
     * @param commentDTO            - DTO модель класса {@link CreateOrUpdateCommentDto
     * @return DTO модель комментария
     */
    @Override
    public CommentDto addCommentToAd(Integer adId, CreateOrUpdateCommentDto commentDTO, Authentication authentication) {
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new AdNotFoundException("Объявление не найдено"));
        Comment comment = commentMapper.createOrUpdateCommentDTOToComment(commentDTO);
        comment.setAd(ad);
        comment.setAuthor(user);
        comment.setText(comment.getText());
        return commentMapper.commentToCommentDTO(commentRepository.save(comment));
    }

    /**
     * Метод удаляет комментарий
     *
     * @param commentId - id комментария
     * @return строку с результатом выполнения метода
     */
    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId) {
        commentRepository.deleteByAdIdAndId(adId, commentId);
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto commentDTO) {
        Comment updateComment = commentRepository.findByAdIdAndId(adId, commentId);
        Comment comment = commentMapper.createOrUpdateCommentDTOToComment(commentDTO);
        updateComment.setText(comment.getText());
        updateComment.setCreatedAt((int) (Instant.now().toEpochMilli() / 1000L));
        return commentMapper.commentToCommentDTO(commentRepository.save(updateComment));
    }
}
