package com.kimsongnam.mindset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private String userFirstName;
    private String userLastName;
    private String userUserName;
    @Pattern(regexp = "^(\\+[0-9]{2,}[0-9]{4,}[0-9]*)(x?[0-9]{1,})?$", message = "일치하지 않는 형식입니다.")
    private String userPhone;
    private String userGender;
}
