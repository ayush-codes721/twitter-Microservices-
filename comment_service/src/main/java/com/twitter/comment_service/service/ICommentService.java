package com.twitter.comment_service.service;

import com.twitter.comment_service.DTO.AddCommentRequestDTO;
import com.twitter.comment_service.DTO.CommentDTO;
import com.twitter.comment_service.DTO.EditCommentRequestDTO;

import java.util.List;

public interface ICommentService {

    CommentDTO createComment(AddCommentRequestDTO addCommentRequest,Long userId);

    List<CommentDTO> getCommentsForATweet(Long tweetId);

    void deleteComment(Long commentId, Long userId);

    Long countCommentsForATweet(Long tweetId);

    CommentDTO editComment(Long commentId, Long userId, EditCommentRequestDTO editCommentRequest);

}
