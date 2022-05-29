package com.hellowalnut.assessment.resource;

import java.util.Set;

import org.springframework.web.reactive.function.client.WebClient;

import com.hellowalnut.assessment.model.Post;
import com.hellowalnut.assessment.model.Posts;

import reactor.core.publisher.Mono;

public class PostResource implements Runnable {

    private static String baseUrl = "https://api.hatchways.io/assessment/blog/posts?tag=";

    private static WebClient client = WebClient.create();

    private String tag;

    private Set<Post> uniquePosts;


    public PostResource(Set<Post> posts, String tag) {
        this.uniquePosts = posts;
        this.tag = tag.trim();
    }

    public void fetchPosts() {
        Mono<Posts> monoPosts = PostResource.client.get().uri(PostResource.baseUrl + this.tag).retrieve().bodyToMono(Posts.class);
        this.uniquePosts.addAll(monoPosts.block().getPosts());
    }

    @Override
    public void run() {
        this.fetchPosts();
    }
}
