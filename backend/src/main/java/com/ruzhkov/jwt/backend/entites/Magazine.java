package com.ruzhkov.jwt.backend.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "magazine")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String topic;
    private String title;
    private String description;
    private String img;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(
            cascade = {CascadeType.DETACH},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_magazine",
            joinColumns = @JoinColumn(name = "id_magazine"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private List<User> userList;
}
