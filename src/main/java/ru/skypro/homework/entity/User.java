package ru.skypro.homework.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import ru.skypro.homework.config.WebSecurityConfig;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Slf4j
@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(includeFieldNames=true)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image; // один пользователь - одно изображение
}
