package com.hellowalnut.assessment.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hellowalnut.assessment.model.Post;
import com.hellowalnut.assessment.response.ErrorResponse;
import com.hellowalnut.assessment.response.SuccessResponse;
import com.hellowalnut.assessment.service.PostService;

@CrossOrigin
@RestController
@RequestMapping("api/")
public class PostsController {

    @GetMapping(path = "ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(true));
    }

    @GetMapping(path = "posts")
    public ResponseEntity<?> getPosts(
            @RequestParam(name = "tags", required = false, defaultValue = "") String suppliedTags,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") String sortDirection) {

        if(suppliedTags.length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Tags parameter is required"));
        }


        String[] listOfTags = suppliedTags.split(",");

        ExecutorService executorServicePool = Executors.newCachedThreadPool();

        // store only unique posts in thread safe way
        Set<Post> uniquePosts = ConcurrentHashMap.newKeySet();

        // submit the job to different threads for each tag
        for(int i=0; i<listOfTags.length; i++) {
            PostService ps = new PostService(uniquePosts, listOfTags[i]);

            try {
                executorServicePool.execute(ps);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        // wait for fetching results
        try {
            executorServicePool.shutdown();
            executorServicePool.awaitTermination(5000, TimeUnit.SECONDS);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // create list to sort
        List<Post> fetchedPosts = new ArrayList<>(uniquePosts);

        switch(sortBy)
        {
        case "id": {
            fetchedPosts.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
        }; break;

        case "reads": {
            fetchedPosts.sort((o1, o2) -> o1.getReads().compareTo(o2.getReads()));
        }; break;

        case "likes": {
            fetchedPosts.sort((o1, o2) -> o1.getLikes().compareTo(o2.getLikes()));
        }; break;

        case "popularity": {
            fetchedPosts.sort((o1, o2) -> o1.getPopularity().compareTo(o2.getPopularity()));
        }; break;

        default: {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("sortBy parameter is invalid"));
        }
        }

        if(sortDirection.equals("desc")) {
            Collections.reverse(fetchedPosts);
        } else if(!sortDirection.equals("asc")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("direction parameter is invalid"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(fetchedPosts);
    }
}
