package com.kimsongnam.mindset.entity.user;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="USER_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userId;

    @Column(length = 40, nullable = false, name="user_email", unique = true)
    private String userEmail;

    @Column(length = 60, nullable = false, name="user_password")
    private String userPassword;

    @Column(length = 20, nullable = false, name = "user_first_name")
    private String userFirstName;

    @Column(length = 20, nullable = false, name = "user_last_name")
    private String userLastName;

    @Column(length = 20, nullable = false, name = "user_username", unique = true)
    private String userUsername;

    @Column(length = 20, name="user_phone", unique = true)
    private String userPhone;

    @Column(name="user_birth")
    private LocalDate userBirth;

    @Column(name="user_gender")
    private UserGender userGender;
}
