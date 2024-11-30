package com.kritica.jpa.payload;

import java.util.List;

public class SocialUserResponse {

    List<SocialUserDTO> users;

    public List<SocialUserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<SocialUserDTO> users) {
        this.users = users;
    }
}
