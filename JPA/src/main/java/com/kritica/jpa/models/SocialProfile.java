package com.kritica.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    //this will create foreign key of social user class in SocialProfile table
    @OneToOne
    @JoinColumn(name="social_user_id")
    @JsonIgnore
    private SocialUsers socialUser;

    public SocialProfile(Long id, String name, int age, SocialUsers socialUser) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.socialUser = socialUser;
    }

    public SocialProfile(String name, int age, SocialUsers socialUser) {

        this.name = name;
        this.age = age;
        this.socialUser = socialUser;
    }

    public SocialProfile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SocialUsers getSocialUser() {
        return socialUser;
    }

    public void setSocialUser(SocialUsers socialUser) {
        this.socialUser = socialUser;
    }
}
