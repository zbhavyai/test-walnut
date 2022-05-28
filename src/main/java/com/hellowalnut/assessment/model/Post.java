package com.hellowalnut.assessment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

    private int id;

    private String author;

    private int authorId;

    private long likes;

    private double popularity;

    private long reads;

    // private List<Tag> tags;
    private String[] tags;

    //    public String[] getTags() {
    //        String[] tags = new String[this.tags.size()];
    //
    //        for (int i = 0; i < this.tags.size(); i++) {
    //            tags[i] = this.tags.get(i).getTagName();
    //        }
    //
    //        return tags;
    //    }

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
}
