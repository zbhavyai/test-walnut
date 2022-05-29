package com.hellowalnut.assessment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

    private Integer id;

    private String author;

    private Integer authorId;

    private Long likes;

    private Double popularity;

    private Long reads;

    private String[] tags;

    @Override
    public boolean equals(Object obj) {

        if(this == obj) {
            return true;
        }

        if(obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Post otherPost = (Post) obj;

        if(this.getId() == otherPost.getId()) {
            return true;
        }

        return false;
    }


    @Override
    public int hashCode() {
        return this.id;
    }
}
