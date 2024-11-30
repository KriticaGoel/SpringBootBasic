package com.kritica.jpa.payload;

import com.kritica.jpa.models.Post;
import com.kritica.jpa.models.SocialGroup;
import com.kritica.jpa.models.SocialProfile;
import com.kritica.jpa.models.SocialUsers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SocialUserDTO {

    private Long user_id;

    private String username;
    private SocialProfile socialProfile;
    private List<Post> posts= new ArrayList<Post>();
    private Set<SocialGroup> groups= new HashSet<SocialGroup>();

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

    public SocialProfile getSocialProfile() {
        return socialProfile;
    }

    public void setSocialProfile(SocialProfile socialProfile) {
        this.socialProfile = socialProfile;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<SocialGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<SocialGroup> groups) {
        this.groups = groups;
    }
}
