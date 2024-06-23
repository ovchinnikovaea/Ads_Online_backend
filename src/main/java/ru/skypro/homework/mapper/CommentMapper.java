package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.service.ImageService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class CommentMapper {
    @Autowired
    ImageService imageService;
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "authorImage", expression = "java(comment.getAuthor() != null && comment.getAuthor().getImage() != null ? imageService.getUserImageUrl(comment.getAuthor().getImage().getId()) : null)")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    public abstract CommentDto commentToCommentDTO(Comment comment);


    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "pk", target = "id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "ad", ignore = true)
    public abstract Comment commentDTOToComment(CommentDto commentDTO);

    public abstract List<CommentDto> commentsToCommentDTOs(List<Comment> comments);

    public abstract List<Comment> commentDTOsToComments(List<CommentDto> commentDtos);

    @Mapping(target = "results", source = "comments")
    @Mapping(expression = "java(comments.size())", target = "count")
    public  CommentsDto commentsToCommentsDTO(List<Comment> comments) {
        CommentsDto commentsDTO = new CommentsDto();
        commentsDTO.setResults(commentsToCommentDTOs(comments));
        commentsDTO.setCount(comments.size());
        return commentsDTO;
    }

    public abstract CreateOrUpdateCommentDto commentToCreateOrUpdateCommentDTO(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(mapToEpochMillis(java.time.LocalDateTime.now()))")
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    public abstract Comment createOrUpdateCommentDTOToComment(CreateOrUpdateCommentDto createOrUpdateCommentDTO);

    public Long mapToEpochMillis(LocalDateTime dateTime) {
        LocalDateTime adjustedDateTime = dateTime.minusHours(3);
        return adjustedDateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
