package com.hellowalnut.assessment.service;

import org.springframework.web.reactive.function.client.WebClient;

import com.hellowalnut.assessment.model.Posts;

import reactor.core.publisher.Mono;

public class PostService implements Runnable {

    private String baseUrl = "https://api.hatchways.io/assessment/blog/posts?tag=";

    private WebClient client;

    private String tag;

    private Posts posts;


    public PostService(String tag) {
        this.client = WebClient.create();
        this.tag = tag.trim();
    }

    public void fetchPosts() {
        Mono<Posts> monoPosts = this.client.get().uri(this.baseUrl + this.tag).retrieve().bodyToMono(Posts.class);
        this.posts = monoPosts.block();
        System.out.printf("Fetched %d records for tag=\'%s\'%n", this.posts.getPosts().size(), this.tag);
    }

    public Posts getPosts() {
        return this.posts;
    }

    @Override
    public void run() {
        this.fetchPosts();
    }
}
