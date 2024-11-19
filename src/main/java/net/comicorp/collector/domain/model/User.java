package net.comicorp.collector.domain.model;

import jakarta.persistence.*;
import lombok.*;
import net.comicorp.collector.constant.Status;

import java.util.Date;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "cover")
    private String cover;

    @Column(name = "about_you")
    private String aboutYou;

    @Column(name = "signup_date")
    private Date signUpDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
