package com.kimsongnam.mindset.service;

import com.kimsongnam.mindset.dto.request.*;
import com.kimsongnam.mindset.dto.response.OnedayMoodResponse;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
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
            throw new ForbiddenException("?????? ????????? ????????????.");
        }
        moodRepository.deleteById(moodId);
    }

    @Transactional
    public void UpdateMood(long moodId, UpdateMoodRequest updateMoodRequest, BindingResult bindingResult){
        Mood mood = findMood(moodId);
        formValidation(bindingResult);
        if(mood.getUserId().getUserId()!= updateMoodRequest.getUserId()){
            throw new ForbiddenException("?????? ????????? ????????????.");
        }

        String moodTitle = updateMoodRequest.getMoodTitle();
        String moodReason = updateMoodRequest.getMoodReason();
        if(updateMoodRequest.getMoodTitle().isEmpty()){
            moodTitle = mood.getMoodTitle();
        }
        if(updateMoodRequest.getMoodReason().isEmpty()){
            moodReason = mood.getMoodReason();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(updateMoodRequest.getMoodDate(), formatter);
        } catch (Exception e) {
            throw new BadRequesetException("????????? ???????????????.");
        }

        LocalTime time = mood.getMoodDate().toLocalTime();
        LocalDateTime localDateTime = localDate.atTime(time);

        mood.updateMood(moodTitle, moodReason, localDateTime);
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
            throw new BadRequesetException("????????? ???????????????.");
        }

        return moodRepository.findMoodAllByUserId(user, yearMonth);
    }

    @Transactional
    public List<OnedayMoodResponse> OnedayMood(long userId, OnedayMoodRequest onedayMoodRequest, BindingResult bindingResult){
        formValidation(bindingResult);
        User user = findUser(userId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(onedayMoodRequest.getMoodDate(), formatter);
        } catch (Exception e) {
            throw new BadRequesetException("????????? ???????????????.");
        }

        return moodRepository.findOnedayMoodResponses(user, localDate);
    }

    public void formValidation(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequesetException("???????????? ?????? ????????? ????????????.");
        }
    }

    public MoodCategory enumCategoryValid(String category) {
        try {
            return MoodCategory.valueOf(category);
        } catch (Exception e) {
            throw new NotFoundException("???????????? ?????? ???????????????.");
        }
    }

    public MoodSituation enumSituationValid(String moodSituation) {
        try {
            return MoodSituation.valueOf(moodSituation);
        } catch (Exception e) {
            throw new NotFoundException("???????????? ?????? ????????? ???????????????.");
        }
    }

    public User findUser(long userId){
        return userRepository.findById(userId).orElseThrow(()-> new NotFoundException("???????????? ?????? ???????????????."));
    }

    public Mood findMood(long moodId){
        return moodRepository.findById(moodId).orElseThrow(()-> new NotFoundException("???????????? ?????? ???????????????."));
    }
}
