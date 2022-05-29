package com.hellowalnut.assessment.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import com.hellowalnut.assessment.model.Post;
import com.hellowalnut.assessment.model.Posts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsControllerTest {

    @Autowired
    private PostsController postController;

    @Autowired
    private WebTestClient client;

    @Test
    public void textContextLoads() {
        assertTrue(this.postController != null, "PostsController not injected");
    }

    @Test
    public void testPing() {
        this.client.get().uri("/api/ping").exchange().expectStatus().isOk();
    }

    @Test
    public void testNoTagPresent() {
        this.client.get()
                .uri("/api/posts")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testIsTagPresent() {
        Posts expectedPosts = this.client.get()
                .uri("/api/posts?tags=tech")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Posts.class)
                .returnResult().getResponseBody();

        for (Post p : expectedPosts.getPosts()) {
            assertTrue(Arrays.stream(p.getTags()).anyMatch("tech"::equals), "tech tag not present in the results");
        }
    }

    @Test
    public void testDefaultSorting() {
        Posts expectedPosts = this.client.get()
                .uri("/api/posts?tags=culture")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Posts.class)
                .returnResult().getResponseBody();

        int previousId = Integer.MIN_VALUE;

        for (Post p : expectedPosts.getPosts()) {
            assertTrue(p.getId() > previousId, "Posts are not sorted in ascending order");

            previousId = p.getId();
        }
    }

    @Test
    public void testDefaultDescSorting() {
        Posts expectedPosts = this.client.get()
                .uri("/api/posts?tags=culture&direction=desc")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Posts.class)
                .returnResult().getResponseBody();

        int previousId = Integer.MAX_VALUE;

        for (Post p : expectedPosts.getPosts()) {
            assertTrue(p.getId() < previousId, "Posts are not sorted in descending order by id");

            previousId = p.getId();
        }
    }

    @Test
    public void testDefaultInvalidSorting() {
        this.client.get()
                .uri("/api/posts?tags=tech&sortBy=invalid")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testUniqueness() {
        Posts expectedPosts = this.client.get()
                .uri("/api/posts?tags=tech,tech")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Posts.class)
                .returnResult().getResponseBody();

        int previousId = Integer.MIN_VALUE;

        for (Post p : expectedPosts.getPosts()) {
            assertTrue(p.getId() > previousId, "Duplicate post found");

            previousId = p.getId();
        }
    }

    @Test
    public void testPopularitySort() {
        Posts expectedPosts = this.client.get()
                .uri("/api/posts?tags=science&sortBy=popularity&direction=desc")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Posts.class)
                .returnResult().getResponseBody();

        double previousPopularity = Double.MAX_VALUE;

        for (Post p : expectedPosts.getPosts()) {
            assertTrue(p.getPopularity() <= previousPopularity,
                    "Posts are not sorted in descending order by popularity");

            previousPopularity = p.getPopularity();
        }
    }
}
