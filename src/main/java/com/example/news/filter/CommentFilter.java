package com.example.news.filter;

import com.example.news.validation.CommentFilterValid;
import lombok.Data;

@Data
@CommentFilterValid
public class CommentFilter {

    private Long newsId;
}
