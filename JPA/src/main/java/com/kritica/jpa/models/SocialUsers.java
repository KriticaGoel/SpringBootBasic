package com.kritica.jpa.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class SocialUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;
    private String password;

    //Tell SocialUsers class that there is one to one mapping with SocialProfile class.
    //mappedBy should be the same as field name in SocialProfile class
    @OneToOne(mappedBy = "socialUser", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private SocialProfile socialProfile;

    @OneToMany(mappedBy = "socialUser", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<Post>();

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<SocialGroup> groups = new HashSet<SocialGroup>();

    public SocialUsers() {
    }

    public SocialUsers(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SocialProfile getSocialProfile() {
        return socialProfile;
    }
    //in SocialProfile we have a SocialUser id as a foreign key. to set its value we need to create a custom method
    public void setSocialProfile(SocialProfile socialProfile) {
        // this.socialProfile = socialProfile;
        socialProfile.setSocialUser(this);
        this.socialProfile = socialProfile;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        posts.stream().forEach(post -> post.setSocialUser(this));
        this.posts = posts;
    }

    public Set<SocialGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<SocialGroup> groups) {
        this.groups = groups;
    }


}

