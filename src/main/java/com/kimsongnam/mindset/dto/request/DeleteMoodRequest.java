package com.kimsongnam.mindset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteMoodRequest {
    @NotNull
    private long userId;
}
