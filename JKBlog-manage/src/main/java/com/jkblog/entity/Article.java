package com.jkblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {

    private String create_time;
    private Integer id;
    private String content;
    private Integer userId;
    private String type;
}