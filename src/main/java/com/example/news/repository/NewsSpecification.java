package com.example.news.repository;

import com.example.news.filter.NewsFilter;
import com.example.news.model.News;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilter filter){
        return Specification
                .where(byRankId(filter.getRankId()))
                .and(byUserId(filter.getUserId()));
    }

    static Specification<News> byUserId(Long userId) {
        return (root, query, criteriaBuilder) -> {
            if(userId == null) return null;
            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        };
    }

    static Specification<News> byRankId(Long rankId) {
        return (root, query, criteriaBuilder) -> {
            if(rankId == null) return null;
            return criteriaBuilder.equal(root.get("rank").get("id"), rankId);
        };
    }
}
