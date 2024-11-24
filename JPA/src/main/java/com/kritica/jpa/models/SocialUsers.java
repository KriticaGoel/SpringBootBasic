package com.kritica.jpa.models;

import jakarta.persistence.*;


@Entity
public class SocialUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;
    private String password;

    //Tell SocialUsers class that there is one to one mapping with SocialProfile class.
    //mappedBy should be the same as field name in SocialProfile class
    @OneToOne(mappedBy = "socialUser")
    private SocialProfile socialProfile;

}
