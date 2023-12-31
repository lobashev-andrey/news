package com.example.news.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseShort {

    private Long id;
    private String name;
    private String text;
    private Long rankId;
    private Long userId;
    private Integer commentsCount;
}