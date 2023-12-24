package com.example.news.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {

    private Long id;
    private String name;
    private String text;
    private Long rankId;
    private Long userId;
    private List<CommentResponse> comments = new ArrayList<>();
}
