package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
@CrossOrigin//(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentsController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    public CommentsController(CommentService commentService, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
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
    public ResponseEntity<CommentsDto> getAllCommentsByAd(@PathVariable int id) {
        CommentsDto commentsDTO = commentService.getAllCommentsByAd(id);
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
                                                 @RequestBody CreateOrUpdateCommentDto comment,
                                                 Authentication authentication) {
        CommentDto commentDTO = commentService.addCommentToAd(id, comment, authentication);
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
    @PreAuthorize("hasRole('ADMIN') or @commentRepository.findById(#commentId)?.get()?.author?.username == principal.username")
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
//    @PreAuthorize("hasRole('ADMIN') or @commentRepository.findById(#commentId).orElse(null)?.author?.username == principal.username")
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto comment) {
        CommentDto commentDTO = commentService.updateComment(adId, commentId, comment);
        return ResponseEntity.ok(commentDTO);
    }


}
