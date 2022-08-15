package com.kimsongnam.mindset.entity.mood.repository;

import com.kimsongnam.mindset.dto.response.RankMoodResponse;
import com.kimsongnam.mindset.entity.user.User;

import java.time.YearMonth;
import java.util.List;

public interface MoodRepositoryExtension {
    List<RankMoodResponse> findMoodAllByUserId(User userId, YearMonth date);
}
