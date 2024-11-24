package com.kritica.jpa.models;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "social_user_id")
    private SocialUsers socialUser;

    private String post;

   
}
