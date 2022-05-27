package com.hellowalnut.assessment.repository;

import com.hellowalnut.assessment.model.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
