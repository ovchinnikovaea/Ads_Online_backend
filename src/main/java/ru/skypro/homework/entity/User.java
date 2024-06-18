package ru.skypro.homework.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
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
    private Role role;
    private boolean enabled;
    @OneToOne
    private Image image;



    public User(@NonNull Integer id,@NonNull String email,@NonNull String firstName,
                @NonNull String lastName,@NonNull String phone,@NonNull Role role,@NonNull Image image) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.image = image;
    }

    public User(@NonNull String firstName,@NonNull String lastName,@NonNull String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }


    public void setPassword(String encode) {

    }

    public String getPassword() {
        return null;
    }
}
