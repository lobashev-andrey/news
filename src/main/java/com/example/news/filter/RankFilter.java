package com.example.news.filter;

import com.example.news.validation.RankFilterValid;
import lombok.Data;

@Data
@RankFilterValid
public class RankFilter {

    private Integer pageSize;
    private Integer pageNumber;
}
