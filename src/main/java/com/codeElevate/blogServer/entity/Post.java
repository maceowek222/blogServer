package com.codeElevate.blogServer.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 5000)
    private String content;

    private String postetBy;

    private String image;


    private int likeCount;

    private int viewCount;

    private String tags;


    public Post() {

    }
}
