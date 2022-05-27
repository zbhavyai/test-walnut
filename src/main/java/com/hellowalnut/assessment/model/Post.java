package com.hellowalnut.assessment.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a blog post
 */
@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    @Column(name = "author")
    private String author;

    @Column(name = "author_id")
    private int authorId;

    @Column(name = "likes_count")
    private long likes;

    @Column(name = "popularity")
    private double popularity;

    @Column(name = "reads_count")
    private long reads;

    @ManyToMany
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "post_id", foreignKey = @ForeignKey(name = "fk_posttag_post")), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tag_id", foreignKey = @ForeignKey(name = "fk_posttag_tag")))
    private List<Tag> tags;

    public String[] getTags() {
        String[] tags = new String[this.tags.size()];

        for (int i = 0; i < this.tags.size(); i++) {
            tags[i] = this.tags.get(i).getTagName();
        }

        return tags;
    }
}
