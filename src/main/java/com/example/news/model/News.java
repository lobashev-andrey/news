package com.example.news.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String text;

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private Rank rank;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();
}
