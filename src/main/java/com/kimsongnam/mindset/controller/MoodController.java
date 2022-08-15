package com.kimsongnam.mindset.controller;

import com.kimsongnam.mindset.dto.request.*;
import com.kimsongnam.mindset.dto.response.RankMoodResponse;
import com.kimsongnam.mindset.service.MoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mood")
public class MoodController {
    private final MoodService moodService;

    @PostMapping("/add-category")
    public void AddMoodCategory(@RequestBody @Valid AddMoodCategoryRequest addMoodCategoryRequest, BindingResult bindingResult){
        moodService.AddMoodCategory(addMoodCategoryRequest, bindingResult);
    }

    @PostMapping("/add-situation")
    public void AddMoodSituation(@RequestBody @Valid AddMoodSituationRequest addMoodSituationRequest, BindingResult bindingResult){
        moodService.AddMoodSituation(addMoodSituationRequest, bindingResult);
    }

    @PostMapping("/add-content")
    public void AddMoodContent(@RequestBody @Valid AddMoodContentRequest addMoodContentRequest, BindingResult bindingResult){
        moodService.AddMoodContent(addMoodContentRequest, bindingResult);
    }

    @DeleteMapping("/delete")
    public void DeleteMood(@RequestParam long moodId, @Valid @RequestBody DeleteMoodRequest deleteMoodRequeset, BindingResult bindingResult){
        moodService.DeleteMood(moodId, deleteMoodRequeset, bindingResult);
    }

    @PutMapping("/update")
    public void UpdateMood(@RequestParam long moodId, @Valid @RequestBody UpdateMoodRequest updateMoodRequest, BindingResult bindingResult){
        moodService.UpdateMood(moodId, updateMoodRequest, bindingResult);
    }

    @GetMapping("/rank")
    public List<RankMoodResponse> RankMood(@RequestParam long userId, @Valid @RequestBody RankMoodRequest rankMoodRequest, BindingResult bindingResult){
        return moodService.RankMood(userId, rankMoodRequest, bindingResult);
    }
}
