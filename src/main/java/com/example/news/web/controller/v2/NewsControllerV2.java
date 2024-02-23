package com.example.news.web.controller.v2;

//import com.example.news.aop.AuthorAccessCheck;
import com.example.news.aop.annotation.NewsDeleteCheck;
import com.example.news.aop.annotation.NewsUpdateCheck;
import com.example.news.filter.NewsFilter;
import com.example.news.mapper.v2.NewsMapperV2;
import com.example.news.service.DatabaseNewsService;
import com.example.news.service.DatabaseUserService;
import com.example.news.web.dto.NewsRequest;
import com.example.news.web.dto.NewsResponse;
import com.example.news.web.dto.NewsResponseList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/news")
public class NewsControllerV2 {

    private final DatabaseNewsService service;
    private final NewsMapperV2 mapper;
    private final DatabaseUserService userService;

    @GetMapping
    public ResponseEntity<NewsResponseList> findAll(@Valid NewsFilter filter){
        return filterBy(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                mapper.newsToResponse(
                        service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody NewsRequest request, @AuthenticationPrincipal UserDetails userDetails){

        request.setUserId(getUserId(userDetails));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.newsToResponse(
                        service.save(
                                mapper.requestToNews(request))));
    }

    @PutMapping("/{id}")
    @NewsUpdateCheck
    public ResponseEntity<NewsResponse> update (@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails, @RequestBody NewsRequest request){

        request.setUserId(getUserId(userDetails));

        return ResponseEntity.ok(
                mapper.newsToResponse(
                        service.update(
                                mapper.requestToNews(id, request))));
    }

    @DeleteMapping("/{id}")
    @NewsDeleteCheck
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/filter")
    public ResponseEntity<NewsResponseList> filterBy(@Valid NewsFilter filter){
        return ResponseEntity.ok(
                mapper.newsListToResponseList(
                        service.filterBy(filter)));
    }

    public Long getUserId(UserDetails userDetails) {
        return userService.findByName(userDetails.getUsername()).getId();
    }
}
