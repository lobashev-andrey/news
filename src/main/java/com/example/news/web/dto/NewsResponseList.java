package com.example.news.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseList {

    private List<NewsResponseShort> news = new ArrayList<>();
}
