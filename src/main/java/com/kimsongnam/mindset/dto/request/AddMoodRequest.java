package com.kimsongnam.mindset.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddMoodRequest {
    @NotNull
    private String moodCategory;

    @NotNull
    private String moodSituation;

    private String moodTitle;

    private String moodReason;

    @NotNull
    @JsonFormat(timezone = "Asia/Seoul", pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate moodDate;

    @NotNull
    private long userId;
}
