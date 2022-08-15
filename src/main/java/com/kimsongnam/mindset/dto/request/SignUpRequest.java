package com.kimsongnam.mindset.dto.request;

import com.kimsongnam.mindset.entity.user.UserGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String userPassword;

    private String userUsername;

    private String userPhone;

    private LocalDate userBirth;

    private UserGender userGender;
}
