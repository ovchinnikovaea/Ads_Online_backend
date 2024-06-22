package ru.skypro.homework.entity;
/*Авторизация*/


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Id
    private Long id;

    @Column(name = "username") // логин
    private String username;

    @Column(name = "authority")
    private String authority;

}
