package com.example.news.web.controller.V1;

import com.example.news.mapper.RankMapper;
import com.example.news.model.Rank;
import com.example.news.service.RankService;
import com.example.news.web.dto.RankRequest;
import com.example.news.web.dto.RankResponse;
import com.example.news.web.dto.RankResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rank")
public class RankController {

    private final RankService rankService;
    private final RankMapper rankMapper;

    @GetMapping
    public ResponseEntity<RankResponseList> findAll(){
        return ResponseEntity.ok(
                rankMapper.rankListToResponseList(
                        rankService.findAll()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RankResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                rankMapper.rankToResponse(
                        rankService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<RankResponse> create(@RequestBody RankRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rankMapper.rankToResponse(
                        rankService.save(
                                rankMapper.requestToRank(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RankResponse> update(@PathVariable Long id, @RequestBody RankRequest request){
        Rank updatedRank = rankService.update(rankMapper.requestToRank(id, request));
        return ResponseEntity.ok(rankMapper.rankToResponse(updatedRank));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        rankService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
