package com.hellowalnut.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hellowalnut.assessment.exception.PostsException;
import com.hellowalnut.assessment.model.Posts;
import com.hellowalnut.assessment.response.ErrorResponse;
import com.hellowalnut.assessment.response.SuccessResponse;
import com.hellowalnut.assessment.service.PostService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PostsController {

    @Autowired
    PostService postService;

    @GetMapping(path = "/ping", produces = "application/json")
    public ResponseEntity<?> ping() {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(true));
    }

    @GetMapping(path = "/posts", produces = "application/json")
    public ResponseEntity<?> getPosts(
            @RequestParam(name = "tags", required = false, defaultValue = "") String suppliedTags,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") String sortDirection) {

        try {
            Posts posts = this.postService.getPosts(suppliedTags, sortBy, sortDirection);
            return ResponseEntity.status(HttpStatus.OK).body(posts);
        }

        catch (PostsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }
    }
}
