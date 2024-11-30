package com.kritica.jpa.service;

import com.kritica.jpa.models.SocialUsers;
import com.kritica.jpa.models.Test;
import com.kritica.jpa.payload.SocialUserResponse;

import java.util.List;

public interface SocialService {
    SocialUserResponse getUsers();

    SocialUsers saveUser(SocialUsers user);

    SocialUsers deleteUserWithId(Long id);

    SocialUsers updateuser(SocialUsers user);
    Test saveTest(Test user);
}
