package com.example.news.filter;

import com.example.news.validation.NewsFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@NewsFilterValid
public class NewsFilter {

    private Long rankId;
    private Long userId;
    private Integer pageNumber;
    private Integer pageSize;
}
