package com.kimsongnam.mindset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateMoodRequest {
    @NotNull
    private long userId;

    private String moodTitle;

    private String moodReason;

    private String moodDate;
}
