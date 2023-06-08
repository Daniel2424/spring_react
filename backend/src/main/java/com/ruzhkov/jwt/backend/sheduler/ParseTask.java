package com.ruzhkov.jwt.backend.sheduler;


import com.ruzhkov.jwt.backend.entites.News;
import com.ruzhkov.jwt.backend.repositories.NewsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ParseTask {

    private final NewsRepository newsRepository;

    @Autowired
    public ParseTask( NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    //@Scheduled(cron = "0 10 0 * * *")

    @Scheduled(fixedDelay = 6000000)
    public void parseAllNews(){
        String[] allUrl = new String[]{"https://habr.com/ru/flows/develop/news/","https://habr.com/ru/flows/admin", "https://habr.com/ru/flows/design/",
        "https://habr.com/ru/flows/management/", "https://habr.com/ru/flows/marketing/", "https://habr.com/ru/flows/popsci/"};
        newsRepository.deleteAll();
        int count = 0;
        String[] topics = new String[]{"development", "administration", "design", "management", "marketing", "science_pop"};
        for(String url: allUrl){
            for (int i = 1; i <= 5 ; i++) {

                String url2 = url +  "page" + i;

                try {
                    Document page = Jsoup.connect(url).get();
                    Elements news = page.getElementsByClass("tm-title__link");
                    System.out.println(news.size() + " - size");

                    for (Element element : news) {

                        News cur_news = new News();
                        cur_news.setTopic(topics[count]);
                        cur_news.setNewsText(element.text());
                        cur_news.setUrl("https://habr.com" + element.attr("href"));
                        newsRepository.save(cur_news);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            count++;
        }

    }
}
