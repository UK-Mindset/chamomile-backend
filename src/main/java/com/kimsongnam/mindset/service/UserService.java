package com.kimsongnam.mindset.service;

import com.kimsongnam.mindset.dto.request.*;
import com.kimsongnam.mindset.entity.user.User;
import com.kimsongnam.mindset.entity.user.repository.UserRepository;
import com.kimsongnam.mindset.exception.BadRequesetException;
import com.kimsongnam.mindset.exception.ConflictException;
import com.kimsongnam.mindset.exception.ForbiddenException;
import com.kimsongnam.mindset.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static SignUpRequest tempUser;

    public void SignUpName(SignUpUserNameRequest signUpUserNameRequest, BindingResult bindingResult){
        formValidation(bindingResult);
        tempUser = new SignUpRequest();
        tempUser.setUserFirstName(signUpUserNameRequest.getUserFirstName());
        tempUser.setUserLastName(signUpUserNameRequest.getUserLastName());
    }

    public void SignUpEmail(SignUpUserEmailRequest signUpUserEmailRequest, BindingResult bindingResult){
        formValidation(bindingResult);
        if(userRepository.existsByUserEmail(signUpUserEmailRequest.getUserEmail())){
            throw new ConflictException("이미 존재하는 계정입니다.");
        }
        tempUser.setUserEmail(signUpUserEmailRequest.getUserEmail());
    }

    public void SignUpPassword(SignUpUserPasswordRequest signUpUserPasswordRequest, BindingResult bindingResult){
        formValidation(bindingResult);

        if(!signUpUserPasswordRequest.getUserPassword().equals(signUpUserPasswordRequest.getUserCheckPassword())){
            throw new ForbiddenException("확인 비밀번호와 비밀번호가 일치하지 않습니다.");
        }

        String encodePassword = passwordEncoder.encode(signUpUserPasswordRequest.getUserPassword());

        tempUser.setUserPassword(encodePassword);
    }

    public void SignUpNickname(SignUpUserNicknameRequest signUpUserNicknameRequest, BindingResult bindingResult){
        formValidation(bindingResult);

        if(userRepository.existsByUserNickname(signUpUserNicknameRequest.getUserNickname())){
            throw new ConflictException("이미 존재하는 별명입니다.");
        }

        tempUser.setUserNickname(signUpUserNicknameRequest.getUserNickname());
    }

    public void SignUpPhone(SignUpUserPhoneRequest signUpUserPhoneRequest){
        if(!signUpUserPhoneRequest.getUserPhone().isEmpty()){
            if(userRepository.existsByUserPhone(signUpUserPhoneRequest.getUserPhone())){
                throw new ConflictException("이미 존재하는 전화번호 입니다.");
            }else{
                tempUser.setUserPhone(signUpUserPhoneRequest.getUserPhone());
            }
        }
    }

    public void SignUpBirth(SignUpUserBirthRequest signUpUserBirthRequest){
        tempUser.setUserBirth(signUpUserBirthRequest.getUserBirth());
    }
    @Transactional
    public void SignUpGender(SignUpUserGenderRequest signUpUserGenderRequest) {
        if (!signUpUserGenderRequest.getUserGender().isEmpty()) {
            tempUser.setUserGender(signUpUserGenderRequest.getUserGender());
        }

        User user = User.builder()
                        .userFirstName(tempUser.getUserFirstName())
                        .userLastName(tempUser.getUserLastName())
                        .userEmail(tempUser.getUserEmail())
                        .userPassword(tempUser.getUserPassword())
                        .userNickname(tempUser.getUserNickname())
                        .userPhone(tempUser.getUserPhone())
                        .userBirth(tempUser.getUserBirth())
                        .userGender(tempUser.getUserGender())
                .build();

        userRepository.save(user);
    }

    public void formValidation(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequesetException("유효하지 않은 형식의 값입니다.");
        }
    }
}
