package com.kimsongnam.mindset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    private LocalDateTime userBirth;

    private String userGender;
}
