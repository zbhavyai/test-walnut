package com.hellowalnut.assessment.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hellowalnut.assessment.exception.PostsException;
import com.hellowalnut.assessment.model.Post;
import com.hellowalnut.assessment.model.Posts;
import com.hellowalnut.assessment.resource.PostResource;

@Service
public class PostService {

    @Cacheable("posts")
    public Posts getPosts(String suppliedTags, String sortBy, String sortDirection) throws PostsException {

        String[] listOfTags = suppliedTags.split(",");

        if (suppliedTags.length() == 0 || listOfTags.length == 0) {
            throw new PostsException("tags parameter is invalid");
        }

        ExecutorService executorServicePool = Executors.newCachedThreadPool();

        // store only unique posts in thread safe way
        Set<Post> uniquePosts = ConcurrentHashMap.newKeySet();

        // submit the job to different threads for each tag
        for (int i = 0; i < listOfTags.length; i++) {
            PostResource ps = new PostResource(uniquePosts, listOfTags[i]);

            try {
                executorServicePool.execute(ps);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // wait for fetching results
        try {
            executorServicePool.shutdown();
            executorServicePool.awaitTermination(5000, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create list to sort
        List<Post> fetchedPosts = new ArrayList<>(uniquePosts);

        switch (sortBy) {
        case "id":
            fetchedPosts.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
            break;

        case "reads":
            fetchedPosts.sort((o1, o2) -> o1.getReads().compareTo(o2.getReads()));
            break;

        case "likes":
            fetchedPosts.sort((o1, o2) -> o1.getLikes().compareTo(o2.getLikes()));
            break;

        case "popularity":
            fetchedPosts.sort((o1, o2) -> o1.getPopularity().compareTo(o2.getPopularity()));
            break;

        default:
            throw new PostsException("sortBy parameter is invalid");
        }

        if (sortDirection.equals("desc")) {
            Collections.reverse(fetchedPosts);
        } else if (!sortDirection.equals("asc")) {
            throw new PostsException("direction parameter is invalid");
        }

        // wrap it in posts object
        Posts posts = new Posts();
        posts.setPosts(fetchedPosts);

        return posts;
    }
}
