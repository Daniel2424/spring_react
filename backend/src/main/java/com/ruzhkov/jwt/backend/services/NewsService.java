package com.ruzhkov.jwt.backend.services;

import com.ruzhkov.jwt.backend.entites.News;
import com.ruzhkov.jwt.backend.repositories.NewsRepository;
import com.ruzhkov.jwt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }


    public List<News> allNews(){
        return newsRepository.findAll();
    }

    public List<News> recommend(String login) {
        var user = userRepository.findByLogin(login).get();
        List<String> list = new ArrayList<>();
        user.getListMagazines().forEach(x-> list.add(x.getTopic()));
        List<News> listNews = new ArrayList<>();
        for(String topic: list){

            listNews.addAll(newsRepository.findAllByTopic(topic));

        }
        Collections.shuffle(listNews);
        return listNews;

    }
}
