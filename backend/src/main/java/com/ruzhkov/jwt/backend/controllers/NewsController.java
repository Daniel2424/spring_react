package com.ruzhkov.jwt.backend.controllers;

import com.ruzhkov.jwt.backend.services.NewsService;
import com.ruzhkov.jwt.backend.entites.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/allNews")
    public ResponseEntity<List<News>> allNews(){
        return ResponseEntity.ok(newsService.allNews());
    }

    @GetMapping("/recommend")
    public ResponseEntity<List<News>> recommend(){
        return ResponseEntity.ok(newsService.recommend(getLogin()));

    }
    private String getLogin(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String json = authentication.getName();
        System.out.println(json);
        var x = json.split(",");
        var y = x[3].split("=");
        var name = y[1];
        return name;
    }
}
