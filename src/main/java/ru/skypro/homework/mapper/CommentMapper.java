package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.comments.CommentDTO;
import ru.skypro.homework.dto.comments.CommentsDTO;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Image;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image.filePath", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    CommentDTO commentToCommentDTO(Comment comment);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "pk", target = "id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "ad", ignore = true)
    Comment commentDTOToComment(CommentDTO commentDTO);

    List<CommentDTO> commentsToCommentDTOs(List<Comment> comments);

    List<Comment> commentDTOsToComments(List<CommentDTO> commentDTOs);

    @Mapping(target = "results", source = "comments")
    @Mapping(expression = "java(comments.size())", target = "count")
    default CommentsDTO commentsToCommentsDTO(List<Comment> comments) {
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setResults(commentsToCommentDTOs(comments));
        commentsDTO.setCount(comments.size());
        return commentsDTO;
    }

    CreateOrUpdateCommentDTO commentToCreateOrUpdateCommentDTO(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java((int) (System.currentTimeMillis() / 1000))")
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    Comment createOrUpdateCommentDTOToComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);
}
