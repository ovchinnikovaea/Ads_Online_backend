package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "pk",source = "id")
    @Mapping(target = "author",expression = "java(comment.getAuthor().getId())")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "authorFirstName", expression = "java(comment.getAuthor().getFirstName())" )
    @Mapping(target = "text", source = "text" )
    @Mapping(target = "authorImage", expression = "java(comment.getAuthor().getImage() != null ? \"/avatars/\"+comment.getAuthor().getId() : null)")
    CommentDto commentToCommentDTO(Comment comment);

    @Mapping(target = "text",source = "text")
    Comment createOrUpdateCommentDTOToComment(CreateOrUpdateCommentDto createOrUpdateCommentDTO);
}
