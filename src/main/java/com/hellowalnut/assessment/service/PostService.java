package com.hellowalnut.assessment.service;

import java.util.Set;

import org.springframework.web.reactive.function.client.WebClient;

import com.hellowalnut.assessment.model.Post;
import com.hellowalnut.assessment.model.Posts;

import reactor.core.publisher.Mono;

public class PostService implements Runnable {

    private static String baseUrl = "https://api.hatchways.io/assessment/blog/posts?tag=";

    private static WebClient client = WebClient.create();

    private String tag;

    private Set<Post> uniquePosts;


    public PostService(Set<Post> posts, String tag) {
        this.uniquePosts = posts;
        this.tag = tag.trim();
    }

    public void fetchPosts() {
        Mono<Posts> monoPosts = PostService.client.get().uri(PostService.baseUrl + this.tag).retrieve().bodyToMono(Posts.class);
        this.uniquePosts.addAll(monoPosts.block().getPosts());
    }

    @Override
    public void run() {
        this.fetchPosts();
    }
}
