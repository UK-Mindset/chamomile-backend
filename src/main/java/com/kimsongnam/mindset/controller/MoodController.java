package com.kimsongnam.mindset.controller;

import com.kimsongnam.mindset.dto.request.AddMoodRequest;
import com.kimsongnam.mindset.dto.request.DeleteMoodRequest;
import com.kimsongnam.mindset.service.MoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mood")
public class MoodController {
    private final MoodService moodService;

    @PostMapping("/add")
    public void AddMood(@RequestBody @Valid AddMoodRequest addMoodRequest, BindingResult bindingResult){
        moodService.AddMood(addMoodRequest, bindingResult);
    }

    @DeleteMapping("/delete")
    public void DeleteMood(@RequestParam long moodId, @Valid @RequestBody DeleteMoodRequest deleteMoodRequest){
        moodService.DeleteMood(moodId, deleteMoodRequest);
    }
}
