package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id") // множество комментариев - один автор
    private User author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "create_at") // дата-время создания
    private Integer createdAt;

    @Column(name = "text") // текст
    private String text;

    @ManyToOne
    @JoinColumn(name = "ads_id") // множество комментариев - одно объявление
    private Ad ad;

    public Comment() {
    }

    public Comment(User author,
                   String text,
                   Ad ad) {
        this.author = author;
        this.createdAt = (int) (Instant.now().toEpochMilli() / 1000L);
        this.text = text;
        this.ad = ad;
    }

}
