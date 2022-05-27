package com.hellowalnut.assessment.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Posts {
    private List<Post> posts;

    public Posts(List<Post> posts) {
        this.posts = posts;
    }
}
