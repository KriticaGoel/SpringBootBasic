package com.kritica.jpa.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SocialGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "groups")
    private Set<SocialUsers>  socialUsers = new HashSet<SocialUsers>();

}
