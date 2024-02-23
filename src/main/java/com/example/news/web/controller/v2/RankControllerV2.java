package com.example.news.web.controller.v2;

import com.example.news.exception.IllegalOperationException;
import com.example.news.filter.RankFilter;
import com.example.news.mapper.v2.RankMapperV2;
import com.example.news.service.DatabaseRankService;
import com.example.news.web.dto.RankRequest;
import com.example.news.web.dto.RankResponse;
import com.example.news.web.dto.RankResponseList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/rank")
public class RankControllerV2 {

    private final DatabaseRankService service;
    private final RankMapperV2 mapper;


    @GetMapping
    public ResponseEntity<RankResponseList> findAll(@Valid RankFilter filter){
        return ResponseEntity.ok(
                mapper.rankListToResponseList(
                        service.findAll(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RankResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                mapper.rankToResponse(
                        service.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<RankResponse> create(@RequestBody RankRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.rankToResponse(
                        service.save(
                                mapper.requestToRank(request))));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<RankResponse> update(@PathVariable Long id, @RequestBody RankRequest request) {
        return ResponseEntity.ok(
                mapper.rankToResponse(
                        service.update(
                                mapper.requestToRank(id, request))));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws IllegalOperationException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
