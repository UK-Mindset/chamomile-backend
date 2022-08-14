package com.kimsongnam.mindset.controller;

import com.kimsongnam.mindset.dto.request.AddMoodRequest;
import com.kimsongnam.mindset.service.MoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
