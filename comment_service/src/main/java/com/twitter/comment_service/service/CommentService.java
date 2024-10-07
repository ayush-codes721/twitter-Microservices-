package com.twitter.comment_service.service;

import com.twitter.comment_service.DTO.AddCommentRequestDTO;
import com.twitter.comment_service.DTO.CommentDTO;
import com.twitter.comment_service.DTO.EditCommentRequestDTO;
import com.twitter.comment_service.clients.TweetClient;
import com.twitter.comment_service.clients.UserClient;
import com.twitter.comment_service.model.Comment;
import com.twitter.comment_service.repo.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final CommentRepo commentRepo;
    private final ModelMapper modelMapper;
    private final TweetClient tweetClient;

    @Override
    public CommentDTO createComment(AddCommentRequestDTO addCommentRequest, Long userId) {

        if (addCommentRequest.getContent() == null || addCommentRequest.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be null or empty");
        }
        tweetClient.getTweetById(addCommentRequest.getTweetId());//it will throw exception if tweet doesn't exist;

        Comment comment = new Comment();
        comment.setContent(addCommentRequest.getContent());
        comment.setUserId(userId);
        comment.setTweetId(addCommentRequest.getTweetId());

        Comment savedComment = commentRepo.save(comment);


        return modelMapper.map(savedComment, CommentDTO.class);
    }


    @Override
    public List<CommentDTO> getCommentsForATweet(Long tweetId) {

        if (tweetId == null) {
            throw new IllegalArgumentException("Tweet id is required");
        }
        tweetClient.getTweetById(tweetId);//it will throw exception if tweet doesn't exist;
        return commentRepo.findByTweetId(tweetId)
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .toList();
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {

        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUserId().equals(userId)) {
            throw new SecurityException("You are not authorized to delete this comment");
        }

        commentRepo.delete(comment);

    }

    @Override
    public Long countCommentsForATweet(Long tweetId) {

        if (tweetId == null) {
            throw new IllegalArgumentException("Tweet id is required");
        }
        tweetClient.getTweetById(tweetId);//it will throw exception if tweet doesn't exist;

        return commentRepo.countByTweetId(tweetId);
    }

    @Override
    public CommentDTO editComment(Long commentId, Long userId, EditCommentRequestDTO editCommentRequest) {

        if (editCommentRequest.getContent() == null || editCommentRequest.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("There is no valid content for editing");
        }

        Comment comment = commentRepo.findById(commentId).
                orElseThrow(() -> new IllegalArgumentException("no comment exist"));

        if (!comment.getUserId().equals(userId)) {
            throw new SecurityException("You are not authorized to edit this comment");
        }
        comment.setContent(editCommentRequest.getContent());
        Comment updatedComment = commentRepo.save(comment);
        return modelMapper.map(updatedComment, CommentDTO.class);
    }
}
