package com.hellowalnut.assessment.controller;

import com.hellowalnut.assessment.model.Posts;
import com.hellowalnut.assessment.repository.PostRepository;
import com.hellowalnut.assessment.response.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PostRepository postRepository;

    // @Autowired
    // private RestTemplate restTemplate;

    // private String url =
    // "https://api.hatchways.io/assessment/blog/posts?tag=tech";

    // public List<Post> getAllPosts() {
    // Post[] response = restTemplate.getForObject(url, Post[].class);

    // return Arrays.asList(response);
    // }

    /**
     * Route 1 for ping
     *
     * @return success response
     */
    @GetMapping(path = "ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(true));
    }

    @GetMapping(path = "posts")
    public ResponseEntity<?> getPosts() {
        Posts p = new Posts(this.postRepository.findAll());

        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    // @GetMapping(path = "posts")
    // public ResponseEntity<?> getPosts() {
    // String URL = "https://api.hatchways.io/assessment/blog/posts?tag=tech";

    // RestTemplate restTemplate = new RestTemplate();

    // // ResponseEntity<String> response = restTemplate.getForEntity(URL,
    // // String.class);
    // Post[] posts = restTemplate.getForObject(URL, Post[].class);

    // // return ResponseEntity.status(HttpStatus.OK).body(this.getAllPosts());
    // return ResponseEntity.status(HttpStatus.OK).body(posts);
    // }

    // @Bean
    // public RestTemplate restTemplate(RestTemplateBuilder builder) {
    // return builder.build();
    // }

    // @GetMapping(path = "posts")
    // public ResponseEntity<?> getPosts() {
    // JsonObject jo =
    // PostsController.fetch("https://api.hatchways.io/assessment/blog/posts?tag=tech");
    // System.out.println();

    // Gson gson = new GsonBuilder().create();
    // gson.fromJson(jo.getAsJsonObject("posts"))
    // }
}
