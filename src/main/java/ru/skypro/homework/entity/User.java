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
    @Column(name = "id") // Id
    private Integer id;

    @Column(name = "email") // адрес эл.почты
    private String email;

    @Column(name = "firstName") // имя
    private String firstName;

    @Column(name = "lastName") // фамилия
    private String lastName;

    @Column(name = "phone") // номер телефона
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role") // привилегии
    private Role role;

    @Column(name = "username") // логин
    private String username;

    @Column(name = "password") // пароль
    private String password;

    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image; // один пользователь - одно изображение

//    @OneToMany(mappedBy = "users")
//    private List<Ad> adEntityList;
}
