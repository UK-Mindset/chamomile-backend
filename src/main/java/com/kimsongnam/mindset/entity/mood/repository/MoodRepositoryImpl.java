package com.kimsongnam.mindset.entity.mood.repository;

import com.kimsongnam.mindset.dto.response.OnedayMoodResponse;
import com.kimsongnam.mindset.dto.response.OnedayMoodStatisticsResponse;
import com.kimsongnam.mindset.dto.response.RankMoodResponse;
import com.kimsongnam.mindset.dto.response.UserTodayEmotionCountResponse;
import com.kimsongnam.mindset.entity.mood.Mood;
import com.kimsongnam.mindset.entity.mood.QMood;
import com.kimsongnam.mindset.entity.user.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class MoodRepositoryImpl extends QuerydslRepositorySupport implements MoodRepositoryExtension{

    private final JPAQueryFactory queryFactory;

    public MoodRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Mood.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<RankMoodResponse> findMoodAllByUserId(User userId, YearMonth date) {
        return queryFactory
                .select(Projections.constructor(
                        RankMoodResponse.class,
                        QMood.mood.moodCategory
                ))
                .from(QMood.mood)
                .where(QMood.mood.moodDate.year().eq(date.getYear()),QMood.mood.moodDate.month().eq(date.getMonthValue()), QMood.mood.userId.userId.eq(userId.getUserId()))
                .groupBy(QMood.mood.moodCategory)
                .orderBy(QMood.mood.moodCategory.count().desc())
                .fetch();
    }

    @Override
    public List<OnedayMoodResponse> findOnedayMoodResponses(User userId, LocalDate localDate){
        return queryFactory
                .select(Projections.constructor(
                        OnedayMoodResponse.class,
                        QMood.mood.moodCategory,
                        QMood.mood.moodSituation,
                        QMood.mood.moodTitle,
                        QMood.mood.moodReason,
                        QMood.mood.moodDate
                ))
                .from(QMood.mood)
                .where(QMood.mood.moodDate.year().eq(localDate.getYear()), QMood.mood.moodDate.month().eq(localDate.getMonthValue()), QMood.mood.moodDate.dayOfMonth().eq(localDate.getDayOfMonth()), QMood.mood.userId.eq(userId))
                .orderBy(QMood.mood.moodDate.asc())
                .fetch();
    }

    @Override
    public List<OnedayMoodStatisticsResponse> findOnedayMoodStatisticsResponses(User userId, LocalDate localDate, long count) {
        long percent = 100/count;
        System.out.println("퍼센트 : " + percent);
        System.out.println("전체 개수 : " + count);

        return queryFactory
                .select(Projections.constructor(
                        OnedayMoodStatisticsResponse.class,
                        QMood.mood.moodCategory,
                        QMood.mood.moodCategory.count().multiply(percent)
                ))
                .from(QMood.mood)
                .where(QMood.mood.moodDate.year().eq(localDate.getYear()), QMood.mood.moodDate.month().eq(localDate.getMonthValue()), QMood.mood.moodDate.dayOfMonth().eq(localDate.getDayOfMonth()), QMood.mood.userId.userId.eq(userId.getUserId()))
                .groupBy(QMood.mood.moodCategory)
                .orderBy(QMood.mood.moodCategory.count().desc())
                .fetch();
    }

    @Override
    public UserTodayEmotionCountResponse findAllCount(User userId, LocalDate localDate) {
        return queryFactory
                .select(Projections.constructor(
                        UserTodayEmotionCountResponse.class,
                        QMood.mood.moodCategory.count()
                ))
                .from(QMood.mood)
                .where(QMood.mood.moodDate.year().eq(localDate.getYear()), QMood.mood.moodDate.month().eq(localDate.getMonthValue()), QMood.mood.moodDate.dayOfMonth().eq(localDate.getDayOfMonth()), QMood.mood.userId.userId.eq(userId.getUserId()))
                .fetchOne();
    }
}
