package com.kimsongnam.mindset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpUserPasswordRequest {
    @NotEmpty
    @Size(max = 16, min = 8)
    private String userPassword;

    @NotEmpty
    private String userCheckPassword;
}
