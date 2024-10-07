package com.twitter.comment_service.controller;

import com.twitter.comment_service.DTO.AddCommentRequestDTO;
import com.twitter.comment_service.DTO.CommentDTO;
import com.twitter.comment_service.DTO.EditCommentRequestDTO;
import com.twitter.comment_service.response.MessageResponse;
import com.twitter.comment_service.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final ICommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<CommentDTO> createComment(@RequestHeader(name = "X-User_Id") Long userId, @RequestBody AddCommentRequestDTO commentRequestDTO) {
        return ResponseEntity.ok(commentService.createComment(commentRequestDTO, userId));
    }

    @GetMapping("/comments/{tweetId}")
    public ResponseEntity<List<CommentDTO>> getCommentsForTweet(@PathVariable Long tweetId) {
        return ResponseEntity.ok(commentService.getCommentsForATweet(tweetId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId, @RequestHeader(name = "X-User_Id") Long userId) {
        commentService.deleteComment(commentId, userId);

        MessageResponse messageResponse = MessageResponse.builder()
                .message("Comment deleted successfully")
                .build();

        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/count/{tweetId}")
    public ResponseEntity<Long> countCommentsForATweet(@PathVariable Long tweetId) {
        return ResponseEntity.ok(commentService.countCommentsForATweet(tweetId));
    }

    @PutMapping("/edit/{commentId}")
    public ResponseEntity<CommentDTO> editComment(@PathVariable Long commentId,
                                                  @RequestHeader(name = "X-User_Id") Long userId,
                                                  @RequestBody EditCommentRequestDTO editCommentRequestDTO) {
        return ResponseEntity.ok(commentService.editComment(commentId, userId, editCommentRequestDTO));
    }
}

