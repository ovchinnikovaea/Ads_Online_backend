package ru.skypro.homework.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comments.CommentDTO;
import ru.skypro.homework.dto.comments.CommentsDTO;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

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

    @Override
    public CommentsDTO getAllCommentsByAuthor(Integer id) {
        List<Comment> comments = commentRepository.findByAdId(id);
        return commentMapper.commentsToCommentsDTO(comments);
    }

    @Override
    public CommentDTO addCommentToAd(Integer adId, CreateOrUpdateCommentDTO commentDTO) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        Comment comment = commentMapper.createOrUpdateCommentDTOToComment(commentDTO);
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new AdsNotFoundException("Объявление не найдено"));
        comment.setAd(ad);
        comment.setText(comment.getText());
        return commentMapper.commentToCommentDTO(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId) {
//        Comment deleteComment = commentRepository.findByAdIdAndId(adId, commentId);
        commentRepository.deleteByAdIdAndId(adId, commentId);
    }

    @Override
    public CommentDTO updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO commentDTO) {
        Comment updateComment = commentRepository.findByAdIdAndId(adId, commentId);
        Comment comment = commentMapper.createOrUpdateCommentDTOToComment(commentDTO);
        updateComment.setText(comment.getText());
        updateComment.setCreatedAt((int) (Instant.now().toEpochMilli() / 1000L));
        return commentMapper.commentToCommentDTO(commentRepository.save(updateComment));
    }
}
