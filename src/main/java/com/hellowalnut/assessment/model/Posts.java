package com.hellowalnut.assessment.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Posts {

    private List<Post> posts = new ArrayList<Post>();
}
