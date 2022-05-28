package com.hellowalnut.assessment.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Posts {

    private List<Post> posts = new ArrayList<Post>();
    //    private Set<Post> posts;

    //    public Posts() {
    //        this.posts = ConcurrentHashMap.newKeySet();
    //    }

    //    public void aggregatePosts(final List<Post> fetchedPosts) {
    //        this.posts.addAll(fetchedPosts);
    //    }

    //    public Set<Post> getPosts() {
    //        return this.posts;
    //    }
}
