package com.ruzhkov.jwt.backend.entites;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "news")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String topic;
    private String title;
    @Column(name = "newstext")
    private String newsText;
    private String url;
}
