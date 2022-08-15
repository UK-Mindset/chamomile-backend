package com.kimsongnam.mindset.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kimsongnam.mindset.entity.mood.MoodCategory;
import com.kimsongnam.mindset.entity.mood.MoodSituation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddMoodRequest {
    @NotNull
    private MoodCategory moodCategory;

    @NotNull
    private MoodSituation moodSituation;

    private String moodTitle;

    private String moodReason;

    @NotNull
    @JsonFormat(timezone = "Asia/Seoul", pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate moodDate;

    @NotNull
    private long userId;
}
