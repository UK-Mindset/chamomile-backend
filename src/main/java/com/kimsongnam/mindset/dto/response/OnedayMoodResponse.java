package com.kimsongnam.mindset.dto.response;

import com.kimsongnam.mindset.entity.mood.MoodCategory;
import com.kimsongnam.mindset.entity.mood.MoodSituation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OnedayMoodResponse {
    private MoodCategory moodCategory;
    private MoodSituation moodSituation;
    private String moodTitle;
    private String moodReason;
    private LocalDateTime moodDate;
}
