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
    @OneToOne(mappedBy = "socialUser")
    private SocialProfile socialProfile;

    @OneToMany(mappedBy = "socialUser")
    private List<Post> posts= new ArrayList<Post>();

    @ManyToMany
    @JoinTable(
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<SocialGroup> groups= new HashSet<SocialGroup>();
}
