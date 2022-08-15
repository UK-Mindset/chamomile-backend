package com.kimsongnam.mindset.entity.mood;

import com.kimsongnam.mindset.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="MOOD_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mood {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="moodId")
    private Long moodId;

    @Column(name="mood_category", nullable = false, columnDefinition = "CHAR(20)")
    @Enumerated(EnumType.STRING)
    private MoodCategory moodCategory;

    @Column(name="mood_situation", nullable = false, columnDefinition = "CHAR(20)")
    @Enumerated(EnumType.STRING)
    private MoodSituation moodSituation;

    @Column(name="mood_title", length=30)
    private String moodTitle;

    @Column(name="mood_reason", length=150)
    private String moodReason;

    @Column(name="mood_date")
    private LocalDateTime moodDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    public void updateMood(String moodTitle, String moodReason){
        this.moodTitle = moodTitle;
        this.moodReason = moodReason;
    }
}
