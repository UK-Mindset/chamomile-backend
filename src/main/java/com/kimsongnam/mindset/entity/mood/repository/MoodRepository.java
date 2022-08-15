package com.kimsongnam.mindset.entity.mood.repository;

import com.kimsongnam.mindset.entity.mood.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodRepository extends JpaRepository<Mood, Long>, MoodRepositoryExtension {

}
