package com.kimsongnam.mindset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpUserPhoneRequest {
    @Pattern(regexp = "^(\\+[0-9]{2,}[0-9]{4,}[0-9]*)(x?[0-9]{1,})?$", message = "일치하지 않는 형식입니다.")
    private String userPhone;
}
