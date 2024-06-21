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
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author; // множество объявлений - один автор

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image images; // одно объявления - множество изображений

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<Comment> comments; // одно объявление - множество комментариев
}
