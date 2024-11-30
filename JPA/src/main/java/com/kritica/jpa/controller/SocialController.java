package com.kritica.jpa.controller;

import com.kritica.jpa.models.SocialUsers;
import com.kritica.jpa.models.Test;
import com.kritica.jpa.payload.SocialUserResponse;
import com.kritica.jpa.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/social")
public class SocialController {
    @Autowired
    private SocialService socialService;

    @GetMapping("/users")
    public ResponseEntity<SocialUserResponse> getUsers() {
        return new ResponseEntity<SocialUserResponse>(socialService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<SocialUsers> createUser(@RequestBody SocialUsers user) {
        return new ResponseEntity<SocialUsers>(socialService.saveUser(user),HttpStatus.CREATED);
    }

    @PostMapping("/test")
    public ResponseEntity<Test> createUser(@RequestBody Test user) {
        return new ResponseEntity<Test>(socialService.saveTest(user),HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<SocialUsers> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<SocialUsers>(socialService.deleteUserWithId(id),HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<SocialUsers> updateUser(@RequestBody SocialUsers user) {
        return new ResponseEntity<SocialUsers>(socialService.updateuser(user),HttpStatus.ACCEPTED);
    }
}
