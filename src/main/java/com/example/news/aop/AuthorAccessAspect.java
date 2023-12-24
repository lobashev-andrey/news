package com.example.news.aop;

import com.example.news.exception.UnautorizedAccessException;
import com.example.news.service.DatabaseUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorAccessAspect {

    private final DatabaseUserService userService;


    @Before("@annotation(AuthorAccessCheck)")
    public void checkAuthorBeforeChangeNews(JoinPoint joinPoint) throws UnautorizedAccessException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        if(notAuthor(request)){
            throw new UnautorizedAccessException("Вы не являетесь автором указанной записи и не можете удалять или редактировать ее");
        }
    }



    public boolean notAuthor(HttpServletRequest request) throws UnautorizedAccessException {
        if(!request.getParameterMap().containsKey("userAuth")) {
            throw new UnautorizedAccessException("Чтобы удалять или редактировать новости и комментарии, необходимо указать userAuth");
        }

        Long userAuth = Long.valueOf(request.getParameter("userAuth"));
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long id = Long.valueOf(pathVariables.get("id"));

        String uri = request.getRequestURI();

        if(uri.contains("/news/")){
            return userService
                    .findById(userAuth)
                    .getNews()
                    .stream()
                    .filter(news -> Objects.equals(news.getId(), id)).toList()
                    .isEmpty();
        }
        return userService
                .findById(userAuth)
                .getComments()
                .stream()
                .filter(comment -> Objects.equals(comment.getId(), id)).toList()
                .isEmpty();
    }
}
