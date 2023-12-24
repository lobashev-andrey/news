package com.example.news.mapper.v2;

import com.example.news.model.Rank;
import com.example.news.web.dto.RankRequest;
import com.example.news.web.dto.RankResponse;
import com.example.news.web.dto.RankResponseList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RankMapperV2 {

    Rank requestToRank(RankRequest request);

    @Mapping(source = "rankId", target = "id")
    Rank requestToRank(Long rankId, RankRequest request);

    RankResponse rankToResponse(Rank rank);

    default RankResponseList rankListToResponseList(List<Rank> ranks){
        return new RankResponseList(ranks.stream().map(this::rankToResponse).toList());
    }
}
