package com.example.news.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long id;
    private String text;
    private News news;
    private User author;
}
