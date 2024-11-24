package com.kritica.jpa.models;

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
    private SocialUsers socialUser;
   
}
