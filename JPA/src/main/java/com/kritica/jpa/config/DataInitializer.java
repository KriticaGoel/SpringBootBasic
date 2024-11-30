package com.kritica.jpa.config;

import com.kritica.jpa.models.Post;
import com.kritica.jpa.models.SocialGroup;
import com.kritica.jpa.models.SocialProfile;
import com.kritica.jpa.models.SocialUsers;
import com.kritica.jpa.repositories.SocialGroupRepository;
import com.kritica.jpa.repositories.SocialPostRepository;
import com.kritica.jpa.repositories.SocialProfileRepository;
import com.kritica.jpa.repositories.SocialUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private SocialUserRepository socialUserRepository;
    @Autowired
    private SocialProfileRepository socialProfileRepository;
    @Autowired
    private SocialPostRepository socialPostRepository;
    @Autowired
    private SocialGroupRepository socialGroupRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner initData(){
    return args -> {

        //Create users
        SocialUsers socialUser1 = new SocialUsers("Kritica","kritica");
        SocialUsers socialUser2 = new SocialUsers("Sachin","Sachin");
        SocialUsers socialUser3 = new SocialUsers("Laavanya","Laavanya");
        //Save data in db
        socialUserRepository.save(socialUser1);
        socialUserRepository.save(socialUser2);
        socialUserRepository.save(socialUser3);

        //Create Profile
        SocialProfile socialProfile1 = new SocialProfile("kritica",35,socialUser1);
        SocialProfile socialProfile2 = new SocialProfile("Sachin",40,socialUser2);
        SocialProfile socialProfile3 = new SocialProfile("Laavanya",2,socialUser3);//Save data in db
        //save data to db
        socialProfileRepository.save(socialProfile1);
        socialProfileRepository.save(socialProfile2);
        socialProfileRepository.save(socialProfile3);

        //Create Post
        Post post1 = new Post(socialUser1,"This is my first post");
        Post post2 = new Post(socialUser1,"This is my second post");
        Post post3 = new Post(socialUser1,"This is my Third post");

        //save to database
        socialPostRepository.save(post1);
        socialPostRepository.save(post2);
        socialPostRepository.save(post3);

        //Create Group
        SocialGroup group1 = new SocialGroup("Java");
        SocialGroup group2 = new SocialGroup("SQL");
        SocialGroup group3 = new SocialGroup("DSA");
        SocialGroup group4 = new SocialGroup("Finance");
        //save to database
        socialGroupRepository.save(group1);
        socialGroupRepository.save(group2);
        socialGroupRepository.save(group3);
        socialGroupRepository.save(group4);
// Associate group with user
        socialUser1.getGroups().add(group1);
        socialUser1.getGroups().add(group2);
        socialUser1.getGroups().add(group3);
        socialUser2.getGroups().add(group4);
// Save user back to database with update association
        socialUserRepository.save(socialUser1);
        socialUserRepository.save(socialUser2);

    };
    }

}
