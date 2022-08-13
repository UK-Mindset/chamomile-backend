package com.kimsongnam.mindset.entity.mood;

import com.kimsongnam.mindset.entity.user.User;
import lombok.*;

import javax.persistence.*;
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

    @Column(name="mood", length = 20, nullable = false)
    private String mood;

    @Column(name="mood_what", length = 20, nullable = false)
    private String moodWhat;

    @Column(name="mood_title", length=30)
    private String moodTitle;

    @Column(name="mood_why", length=150)
    private String moodWhy;

    @Column(name="mood_date")
    private LocalDateTime moodDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;
}
