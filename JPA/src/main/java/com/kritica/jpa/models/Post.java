package com.kritica.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "social_user_id")
    @JsonIgnore
    private SocialUsers socialUser;

    private String post;

    public Post(Long postId, SocialUsers socialUser, String post) {
        this.postId = postId;
        this.socialUser = socialUser;
        this.post = post;
    }
    public Post(SocialUsers socialUser, String post) {

        this.socialUser = socialUser;
        this.post = post;
    }
    public Post(){

    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public SocialUsers getSocialUser() {
        return socialUser;
    }

    public void setSocialUser(SocialUsers socialUser) {
        this.socialUser = socialUser;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
