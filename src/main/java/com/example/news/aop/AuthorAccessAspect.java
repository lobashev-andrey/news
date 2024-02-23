package com.example.news.aop;

import com.example.news.exception.UnauthorizedAccessException;
import com.example.news.service.DatabaseUserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorAccessAspect {

    private final DatabaseUserService userService;
    @Before("@annotation(com.example.news.aop.annotation.UserDataAccessCheck)")
    public void onlySelfOrAdminOrModeratorAccess(JoinPoint joinPoint) throws UnauthorizedAccessException {
        Long askedId = (Long) joinPoint.getArgs()[0];
        UserDetails userDetails = (UserDetails) joinPoint.getArgs()[1];
        Long userId = userService.findByName(userDetails.getUsername()).getId();

        if (!selfAccess(askedId, userId) && notAdminOrModerator(userDetails)) {
            throw new UnauthorizedAccessException("Доступ к записи пользователя имеют: он сам, админ и модератор");
        }
    }
    @Before("@annotation(com.example.news.aop.annotation.NewsUpdateCheck)")
        public void newsUpdateCheck(JoinPoint joinPoint) throws UnauthorizedAccessException {
        Long askedId = (Long) joinPoint.getArgs()[0];
        UserDetails userDetails = (UserDetails) joinPoint.getArgs()[1];
        Long userId = userService.findByName(userDetails.getUsername()).getId();

        if(notAuthorOfNews(askedId, userId)) {
            throw new UnauthorizedAccessException("Редактировать новость может только ее автор");
        }
    }

    @Before("@annotation(com.example.news.aop.annotation.CommentUpdateCheck)")
    public void commentUpdateCheck(JoinPoint joinPoint) throws UnauthorizedAccessException {
        Long askedId = (Long) joinPoint.getArgs()[0];
        UserDetails userDetails = (UserDetails) joinPoint.getArgs()[1];
        Long userId = userService.findByName(userDetails.getUsername()).getId();

        if(notAuthorOfComment(askedId, userId)) {
            throw new UnauthorizedAccessException("Редактировать комментарий может только его автор");
        }
    }

    @Before("@annotation(com.example.news.aop.annotation.NewsDeleteCheck)")
    public void newsDeleteCheck(JoinPoint joinPoint) throws UnauthorizedAccessException {
        Long askedId = (Long) joinPoint.getArgs()[0];
        UserDetails userDetails = (UserDetails) joinPoint.getArgs()[1];
        Long userId = userService.findByName(userDetails.getUsername()).getId();

        if(notAuthorOfNews(askedId, userId) && notAdminOrModerator(userDetails)) {
            throw new UnauthorizedAccessException("Удалить новость могут только ее автор, админ или модератор");
        }
    }

    @Before("@annotation(com.example.news.aop.annotation.CommentDeleteCheck)")
    public void commentDeleteCheck(JoinPoint joinPoint) throws UnauthorizedAccessException {
        Long askedId = (Long) joinPoint.getArgs()[0];
        UserDetails userDetails = (UserDetails) joinPoint.getArgs()[1];
        Long userId = userService.findByName(userDetails.getUsername()).getId();

        if(notAuthorOfComment(askedId, userId) && notAdminOrModerator(userDetails)) {
            throw new UnauthorizedAccessException("Удалить новость могут только ее автор, админ или модератор");
        }
    }
    public boolean notAuthorOfNews(Long id, Long userId) {
         return userService
                 .findById(userId)
                 .getNews()
                 .stream()
                 .filter(news -> Objects.equals(news.getId(), id)).toList()
                 .isEmpty();
    }

    public boolean notAuthorOfComment(Long id, Long userId) {
        return userService
                .findById(userId)
                .getComments()
                .stream()
                .filter(comment -> Objects.equals(comment.getId(), id)).toList()
                .isEmpty();
    }

    public boolean selfAccess(Long id, Long userId) {
        return userId.equals(id);
    }

    public boolean notAdminOrModerator(UserDetails userDetails) {
        List<String> authorities = userDetails
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return !authorities.contains("ROLE_ADMIN") && !authorities.contains("ROLE_MODERATOR");
    }
}
