package ru.skypro.homework.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;

@Component
public class UserSecurity {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    public boolean isAdsAuthor(Integer adsId) {
        try {
            User adAuthor = adRepository.findById(adsId).orElseThrow(AdNotFoundException::new).getAuthor();
            User authentificatedUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: "));

            return adAuthor.equals(authentificatedUser);
        } catch (AdNotFoundException | UserNotFoundException e) {
            return false;
        }
    }

    public boolean isCommentAuthor(Integer commentId) {
        try {
            User commentAuthor = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new).getAuthor();
            User authentificatedUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: "));

            return commentAuthor.equals(authentificatedUser);
        } catch (AdNotFoundException | UserNotFoundException e) {
            return false;
        }
    }
}
