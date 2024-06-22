package ru.skypro.homework.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id") // множество объявлений - один автор
    private User author;

    @Column(name = "price") // цена
    private BigDecimal price;

    @Column(name = "title") // заголовок
    private String title;

    @OneToOne
    @JoinColumn(name = "image_id") // одно объявления - множество изображений
    private Image images;

    @Column(name = "description") // описание
    private String description;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL) // одно объявление - множество комментариев
    private List<Comment> comments;
}
