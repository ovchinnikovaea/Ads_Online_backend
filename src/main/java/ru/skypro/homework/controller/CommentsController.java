package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.CommentService;

@RestController
@RequestMapping("/ads")
public class CommentsController {
    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentsDto.class))),
            @ApiResponse(responseCode = "401",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    content = @Content)

    })

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> getAllCommentsByAuthor(@PathVariable int id) {
        CommentsDto commentsDTO = commentService.getAllCommentsByAuthor(id);
        return ResponseEntity.ok(commentsDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "401",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    content = @Content)
    })
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer id,
                                                 @RequestBody CreateOrUpdateCommentDto comment) {
        CommentDto commentDTO = commentService.addCommentToAd(id, comment);
        return ResponseEntity.ok(commentDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    content = @Content)
    })
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "401",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    content = @Content)
    })
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto comment) {
        CommentDto commentDTO = commentService.updateComment(adId, commentId, comment);
        return ResponseEntity.ok(commentDTO);
    }


}
