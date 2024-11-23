package com.kritica.jpa.models;

import jakarta.persistence.*;

@Entity
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @OneToOne(mappedBy = "socialProfile")
    //@JoinColumn(name = "social_users_id")
    private SocialUsers socialUsers;

}
