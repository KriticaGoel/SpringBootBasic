package com.kritica.jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SocialGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    private Set<SocialUsers>  socialUsers = new HashSet<SocialUsers>();

    private String groupName;


    public SocialGroup(String groupName) {
        this.groupName = groupName;
    }
    public SocialGroup(){

    }

    public SocialGroup(Long id, Set<SocialUsers> socialUsers) {
        this.id = id;
        this.socialUsers = socialUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SocialUsers> getSocialUsers() {
        return socialUsers;
    }

    public void setSocialUsers(Set<SocialUsers> socialUsers) {
        this.socialUsers = socialUsers;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
