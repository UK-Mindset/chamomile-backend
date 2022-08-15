package com.kimsongnam.mindset.entity.mood.repository;

import com.kimsongnam.mindset.dto.response.RankMoodResponse;
import com.kimsongnam.mindset.entity.mood.Mood;
import com.kimsongnam.mindset.entity.mood.QMood;
import com.kimsongnam.mindset.entity.user.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;

public class MoodRepositoryImpl extends QuerydslRepositorySupport implements MoodRepositoryExtension{

    private final JPAQueryFactory queryFactory;

    public MoodRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Mood.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<RankMoodResponse> findMoodAllByUserId(User userId, LocalDate date) {
        return queryFactory
                .select(Projections.constructor(
                        RankMoodResponse.class,
                        QMood.mood.moodCategory
                ))
                .from(QMood.mood)
                .where(QMood.mood.moodDate.year().eq(date.getYear()),QMood.mood.moodDate.month().eq(date.getMonthValue()), QMood.mood.moodDate.dayOfMonth().eq(date.getDayOfMonth()), QMood.mood.userId.userId.eq(userId.getUserId()))
                .groupBy(QMood.mood.moodCategory)
                .orderBy(QMood.mood.moodCategory.count().desc())
                .fetch();
    }
}
