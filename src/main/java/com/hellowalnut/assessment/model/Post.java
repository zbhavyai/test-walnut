package com.hellowalnut.assessment.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
    /**
     * ID of post
     */
    private int id;

    /**
     * Author name of the post
     */
    private String author;

    /**
     * ID of the author of the post
     */
    private int authorId;

    /**
     * Number of likes on the post
     */
    private long likes;

    /**
     * Popularity of the post
     */
    private double popularity;

    /**
     * Number of times post read
     */
    private long reads;

    /**
     * Tags of the post
     */
    private List<String> tags;

    /**
     * Get the popularity of the post
     */
    public String getPopularity() {
        return String.format("%.2f", popularity);
    }
}
