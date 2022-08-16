package com.kimsongnam.mindset.controller;

import com.kimsongnam.mindset.dto.request.*;
import com.kimsongnam.mindset.entity.user.User;
import com.kimsongnam.mindset.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup/name")
    public void SignUpName(@RequestBody @Valid SignUpUserNameRequest signUpUserNameRequest, BindingResult bindingResult) {
        userService.SignUpName(signUpUserNameRequest, bindingResult);
    }

    @PostMapping("/signup/email")
    public void SignUpEmail(@RequestBody @Valid SignUpUserEmailRequest signUpUserEmailRequest, BindingResult bindingResult) {
        userService.SignUpEmail(signUpUserEmailRequest, bindingResult);
    }

    @PostMapping("/signup/password")
    public void SignUpPassword(@RequestBody @Valid SignUpUserPasswordRequest signUpUserPasswordRequest, BindingResult bindingResult) {
        userService.SignUpPassword(signUpUserPasswordRequest, bindingResult);
    }

    @PostMapping("/signup/username")
    public void SignUpUsername(@RequestBody @Valid SignUpUserUsernameRequest signUpUserUsernameRequest, BindingResult bindingResult) {
        userService.SignUpUserName(signUpUserUsernameRequest, bindingResult);
    }

    @PostMapping("/signup/phone")
    public void SignUpPhone(@RequestBody @Valid SignUpUserPhoneRequest signUpUserPhoneRequest) {
        userService.SignUpPhone(signUpUserPhoneRequest);
    }

    @PostMapping("/signup/birth")
    public void SignUpBirth(@RequestBody @Valid SignUpUserBirthRequest signUpUserBirthRequest) {
        userService.SignUpBirth(signUpUserBirthRequest);
    }

    @PostMapping("/signup/gender")
    public void SignUpGender(@RequestBody @Valid SignUpUserGenderRequest signUpUserGenderRequest) {
        userService.SignUpGender(signUpUserGenderRequest);
    }

    @PostMapping("/login")
    public User LoginUser(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult){
        return userService.LoginUser(loginRequest, bindingResult);
    }

    @PutMapping("/update")
    public void UpdateUser(@RequestParam long userId, @RequestBody @Valid UpdateUserRequest updateUserRequest, BindingResult bindingResult){
        userService.UpdateUser(userId, updateUserRequest, bindingResult);
    }
}
