package com.example.news.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class News {

    private Long id;
    private String name;
    private String text;
    private Rank rank;
    private User user;
    private List<Comment> comments = new ArrayList<>();
}
