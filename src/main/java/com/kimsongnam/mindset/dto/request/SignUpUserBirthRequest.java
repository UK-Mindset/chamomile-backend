package com.kimsongnam.mindset.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpUserBirthRequest {
    @JsonFormat(timezone = "Asia/Seoul", pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime userBirth;
}
