package com.kimsongnam.mindset.entity.mood.repository;

import com.kimsongnam.mindset.entity.mood.Mood;
import com.kimsongnam.mindset.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoodRepository extends JpaRepository<Mood, Long>, MoodRepositoryExtension {

}
