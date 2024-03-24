package com.codeElevate.blogServer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 5000)
    private String content;

    private String postetBy;

    private String image;

    private Date date;

    private int likeCount;

    private int viewCount;

    private String tags;

}
