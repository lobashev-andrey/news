package com.example.news.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {

    private String name;
    private Long rank_id;
    private String text;
    private Long user_id;

}
