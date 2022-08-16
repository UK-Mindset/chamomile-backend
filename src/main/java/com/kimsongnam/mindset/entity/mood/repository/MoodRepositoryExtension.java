package com.kimsongnam.mindset.entity.mood.repository;

import com.kimsongnam.mindset.dto.response.OnedayMoodResponse;
import com.kimsongnam.mindset.dto.response.OnedayMoodStatisticsResponse;
import com.kimsongnam.mindset.dto.response.RankMoodResponse;
import com.kimsongnam.mindset.dto.response.UserTodayEmotionCountResponse;
import com.kimsongnam.mindset.entity.user.User;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface MoodRepositoryExtension {
    List<RankMoodResponse> findMoodAllByUserId(User userId, YearMonth date);
    List<OnedayMoodResponse> findOnedayMoodResponses(User userId, LocalDate localDate);
    List<OnedayMoodStatisticsResponse> findOnedayMoodStatisticsResponses(User userId, LocalDate localDate, long count);
    UserTodayEmotionCountResponse findAllCount(User userId, LocalDate localDate);
}
