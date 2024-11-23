package com.kritica.jpa.models;

import jakarta.persistence.*;

@Entity
public class SocialUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    //mapped by is used to tell social user class that there is a one to one mapping
    @OneToOne
    @JoinColumn(name="social_Profile")
    private SocialProfile   socialProfile;
}
