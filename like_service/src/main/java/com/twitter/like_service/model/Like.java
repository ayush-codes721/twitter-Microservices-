package com.twitter.like_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "app_like")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;


    @Column(nullable = false,name = "user_id")
    private Long userId;

    @Column(nullable = false,name = "tweet_id")
    private Long tweetId;

    @CreationTimestamp
    private LocalDateTime likedAt;


}
