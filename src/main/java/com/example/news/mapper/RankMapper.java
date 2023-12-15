package com.example.news.mapper;

import com.example.news.model.Rank;
import com.example.news.service.UserService;
import com.example.news.web.dto.RankRequest;
import com.example.news.web.dto.RankResponse;
import com.example.news.web.dto.RankResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RankMapper {
    
    private final UserService userService;

    public RankResponse rankToResponse(Rank rank){
        RankResponse response = new RankResponse();
        response.setName(rank.getName());
        response.setId(rank.getId());
        response.setAuthor_id(rank.getAuthor().getId());
        return response;
    }

    public Rank requestToRank(RankRequest request){
        Rank rank = new Rank();
        rank.setName(request.getName());
        rank.setAuthor(userService.findById(request.getAuthor_id()));

        return rank;
    }

    public Rank requestToRank(Long id, RankRequest request){
        Rank rank = requestToRank(request);
        rank.setId(id);

        return rank;
    }


    public RankResponseList rankListToResponseList(List<Rank> ranks){
        RankResponseList response = new RankResponseList();
        List<RankResponse> responses = ranks.stream().map(this::rankToResponse).toList();
        response.setRanks(responses);

        return response;
    }
    
    
}
