package com.kimsongnam.mindset.dto.response;

import com.kimsongnam.mindset.entity.mood.MoodCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RankMoodResponse {
    MoodCategory moodCategory;
}
