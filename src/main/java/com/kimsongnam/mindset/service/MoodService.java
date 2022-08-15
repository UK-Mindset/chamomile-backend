package com.kimsongnam.mindset.service;

import com.kimsongnam.mindset.dto.request.*;
import com.kimsongnam.mindset.dto.response.RankMoodResponse;
import com.kimsongnam.mindset.entity.mood.Mood;
import com.kimsongnam.mindset.entity.mood.MoodCategory;
import com.kimsongnam.mindset.entity.mood.MoodSituation;
import com.kimsongnam.mindset.entity.mood.repository.MoodRepository;
import com.kimsongnam.mindset.entity.user.User;
import com.kimsongnam.mindset.entity.user.repository.UserRepository;
import com.kimsongnam.mindset.exception.BadRequesetException;
import com.kimsongnam.mindset.exception.ForbiddenException;
import com.kimsongnam.mindset.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class MoodService {
    private final MoodRepository moodRepository;
    private final UserRepository userRepository;
    private static AddMoodRequest tempMood;

    @Transactional
    public void AddMoodCategory(AddMoodCategoryRequest addMoodCategoryRequest, BindingResult bindingResult){
        formValidation(bindingResult);
        tempMood = new AddMoodRequest();
        MoodCategory category = enumCategoryValid(addMoodCategoryRequest.getMoodCategory());
        tempMood.setMoodCategory(category);
    }

    @Transactional
    public void AddMoodSituation(AddMoodSituationRequest addMoodSituationRequest, BindingResult bindingResult){
        formValidation(bindingResult);
        MoodSituation situation = enumSituationValid(addMoodSituationRequest.getMoodSituation());
        tempMood.setMoodSituation(situation);
    }

    @Transactional
    public void AddMoodContent(AddMoodContentRequest addMoodContentRequest, BindingResult bindingResult){
        formValidation(bindingResult);
        tempMood.setMoodTitle(addMoodContentRequest.getMoodTitle());
        tempMood.setMoodReason(addMoodContentRequest.getMoodReason());

        User user = findUser(addMoodContentRequest.getUserId());

//        LocalTime now = LocalTime.now();
//        LocalDateTime dateTime = addMoodContentRequest.getMoodDate().atTime(now);

        Mood mood = Mood.builder()
                .moodCategory(tempMood.getMoodCategory())
                .moodSituation(tempMood.getMoodSituation())
                .moodTitle(tempMood.getMoodTitle())
                .moodReason(tempMood.getMoodReason())
                .moodDate(now())
                .userId(user)
                .build();

        moodRepository.save(mood);
    }

    @Transactional
    public void DeleteMood(long moodId, DeleteMoodRequest deleteMoodRequest, BindingResult bindingResult){
        Mood mood = findMood(moodId);
        formValidation(bindingResult);
        if(mood.getUserId().getUserId()!= deleteMoodRequest.getUserId()){
            throw new ForbiddenException("삭제 권한이 없습니다.");
        }
        moodRepository.deleteById(moodId);
    }

    @Transactional
    public void UpdateMood(long moodId, UpdateMoodRequest updateMoodRequest, BindingResult bindingResult){
        Mood mood = findMood(moodId);
        formValidation(bindingResult);
        if(mood.getUserId().getUserId()!= updateMoodRequest.getUserId()){
            throw new ForbiddenException("수정 권한이 없습니다.");
        }

        String moodTitle = updateMoodRequest.getMoodTitle();
        String moodReason = updateMoodRequest.getMoodReason();
        if(updateMoodRequest.getMoodTitle().isEmpty()){
            moodTitle = mood.getMoodTitle();
        }
        if(updateMoodRequest.getMoodReason().isEmpty()){
            moodReason = mood.getMoodReason();
        }
        mood.updateMood(moodTitle, moodReason);
    }

    @Transactional
    public List<RankMoodResponse> RankMood(long userId, RankMoodRequest rankMoodRequest, BindingResult bindingResult){
        formValidation(bindingResult);
        User user = findUser(userId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth yearMonth;
        try {
            yearMonth = YearMonth.parse(rankMoodRequest.getMoodDate(), formatter);
        } catch (Exception e) {
            throw new BadRequesetException("잘못된 형식입니다.");
        }

        return moodRepository.findMoodAllByUserId(user, yearMonth);
    }

    public void formValidation(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequesetException("유효하지 않은 형식의 값입니다.");
        }
    }

    public MoodCategory enumCategoryValid(String category) {
        try {
            return MoodCategory.valueOf(category);
        } catch (Exception e) {
            throw new NotFoundException("존재하지 않는 기분입니다.");
        }
    }

    public MoodSituation enumSituationValid(String moodSituation) {
        try {
            return MoodSituation.valueOf(moodSituation);
        } catch (Exception e) {
            throw new NotFoundException("존재하지 않는 형식의 상황입니다.");
        }
    }

    public User findUser(long userId){
        return userRepository.findById(userId).orElseThrow(()-> new NotFoundException("존재하지 않는 회원입니다."));
    }

    public Mood findMood(long moodId){
        return moodRepository.findById(moodId).orElseThrow(()-> new NotFoundException("존재하지 않는 감정입니다."));
    }
}
