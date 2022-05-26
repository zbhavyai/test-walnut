package com.hellowalnut.assessment.controller;

import com.hellowalnut.assessment.response.SuccessResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API wrapper for the posts
 */
@CrossOrigin
@RestController
@RequestMapping("api/")
public class PostsController {

    /**
     * Route 1 for ping
     *
     * @return success response
     */
    @GetMapping(path = "ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(true));
    }
}
