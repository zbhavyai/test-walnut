package com.hellowalnut.assessment.repository;

import com.hellowalnut.assessment.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
