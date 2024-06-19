package ru.skypro.homework.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

@Slf4j
@Entity
@Table(name="users")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(includeFieldNames=true)
public class User {
    @Id
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    @ManyToOne
    private Role role; // множество пользователей - одна роль
    private String username;
    private String password;
    private boolean enabled;
    @OneToOne
    private Image image; // один пользователь - одно изображение

}
