package com.kimsongnam.mindset.entity.mood.repository;

import com.kimsongnam.mindset.dto.response.OnedayMoodResponse;
import com.kimsongnam.mindset.dto.response.RankMoodResponse;
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
                .where(QMood.mood.moodDate.year().eq(localDate.getYear()), QMood.mood.moodDate.month().eq(localDate.getMonthValue()), QMood.mood.moodDate.dayOfMonth().eq(localDate.getDayOfMonth()))
                .orderBy(QMood.mood.moodDate.asc())
                .fetch();
    }
}
