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
//@Entity(name = "users")
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)

    private List<News> news = new ArrayList<>();
}
