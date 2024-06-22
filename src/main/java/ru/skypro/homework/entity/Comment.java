package ru.skypro.homework.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author; // множество комментариев - один автор

    private Long createdAt;
    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad; // множество комментариев - одно объявление

    public Comment() {
    }

    public Comment(User author,
                   String text,
                   Ad ad) {
        this.author = author;
        this.text = text;
        this.ad = ad;
    }

}
