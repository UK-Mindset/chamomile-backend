package com.kimsongnam.mindset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpUserUsernameRequest {
    @NotEmpty
    private String userUsername;
}
