package com.kritica.jpa.service;

import com.kritica.jpa.Exception.APIException;
import com.kritica.jpa.models.SocialUsers;
import com.kritica.jpa.models.Test;
import com.kritica.jpa.payload.SocialUserDTO;
import com.kritica.jpa.payload.SocialUserResponse;
import com.kritica.jpa.repositories.SocialUserRepository;
import com.kritica.jpa.repositories.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialServiceImpl implements SocialService {

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Override
    public SocialUserResponse getUsers() {
        ModelMapper modelMapper = new ModelMapper();
        List<SocialUsers> users = socialUserRepository.findAll();
        if(users.isEmpty()){
            throw(new APIException("No User Found",HttpStatus.NOT_FOUND));
        }
        //convert entity to response model
        List<SocialUserDTO> categoryDTOList = users.stream()
                .map(user -> modelMapper.map(user, SocialUserDTO.class))
                .toList();
        SocialUserResponse response= new SocialUserResponse();
        response.setUsers(categoryDTOList);
       return response;
    }

    @Override
    public SocialUsers saveUser(SocialUsers user) {
        return socialUserRepository.save(user);
    }
@Autowired
    TestRepository testRepository;
    @Override
    public Test saveTest(Test user) {
        return testRepository.save(user);
    }

    @Override
    public SocialUsers deleteUserWithId(Long id) {
        SocialUsers user = socialUserRepository.getOne(id);
        socialUserRepository.deleteById(id);
        return user;
    }

    @Override
    public SocialUsers updateuser(SocialUsers user) {

        socialUserRepository.findById(user.getUser_id()).orElseThrow();
        return socialUserRepository.save(user);
    }
}
