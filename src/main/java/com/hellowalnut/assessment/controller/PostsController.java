package com.hellowalnut.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.hellowalnut.assessment.model.Posts;
import com.hellowalnut.assessment.repository.PostRepository;
import com.hellowalnut.assessment.response.SuccessResponse;

public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(true));
    }

    @GetMapping(path = "posts")
    public ResponseEntity<?> getPosts() {
        Posts p = new Posts(this.postRepository.findAll());

        return ResponseEntity.status(HttpStatus.OK).body(p);
    }
}
