package com.kimsongnam.mindset.service;

import com.kimsongnam.mindset.dto.request.*;
import com.kimsongnam.mindset.entity.user.User;
import com.kimsongnam.mindset.entity.user.UserGender;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public void SignUpUserName(SignUpUserUsernameRequest signUpUserUsernameRequest, BindingResult bindingResult){
        formValidation(bindingResult);

        if(userRepository.existsByUserUsername(signUpUserUsernameRequest.getUserUsername())){
            throw new ConflictException("이미 존재하는 별명입니다.");
        }

        tempUser.setUserUsername(signUpUserUsernameRequest.getUserUsername());
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(signUpUserBirthRequest.getUserBirth(), formatter);
        } catch (Exception e) {
            throw new BadRequesetException("잘못된 형식입니다.");
        }
        tempUser.setUserBirth(localDate);
    }

    @Transactional
    public void SignUpGender(SignUpUserGenderRequest signUpUserGenderRequest) {
        UserGender userGender = enumGenderValid(signUpUserGenderRequest.getUserGender());
        if (!signUpUserGenderRequest.getUserGender().isEmpty()) {
            tempUser.setUserGender(userGender);
        }

        User user = User.builder()
                        .userFirstName(tempUser.getUserFirstName())
                        .userLastName(tempUser.getUserLastName())
                        .userEmail(tempUser.getUserEmail())
                        .userPassword(tempUser.getUserPassword())
                        .userUsername(tempUser.getUserUsername())
                        .userPhone(tempUser.getUserPhone())
                        .userBirth(tempUser.getUserBirth())
                        .userGender(tempUser.getUserGender())
                .build();

        userRepository.save(user);
    }

    @Transactional
    public User LoginUser(LoginRequest loginRequest, BindingResult bindingResult){
        formValidation(bindingResult);

        User user = userRepository.findByUserEmail(loginRequest.getUserEmail()).orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다"));

        if(!passwordEncoder.matches(loginRequest.getUserPassword(), user.getUserPassword())){
            throw new ForbiddenException("회원 정보가 일치하지 않습니다.");
        }

        return user;

    }

    @Transactional
    public void UpdateUser(long userId, UpdateUserRequest updateUserRequest, BindingResult bindingResult){
        formValidation(bindingResult);
        User user = findUser(userId);

        String userFirstName = updateUserRequest.getUserFirstName();
        if(userFirstName.isEmpty()){
            userFirstName = user.getUserFirstName();
        }

        String userLastName = updateUserRequest.getUserLastName();
        if(userLastName.isEmpty()){
            userLastName = user.getUserLastName();
        }

        String userUserName = updateUserRequest.getUserUserName();
        if(userUserName.isEmpty()){
            userUserName = user.getUserUsername();
        }
        if(userRepository.existsByUserUsername(userUserName)){
            throw new ConflictException("이미 존재하는 회원 이름입니다.");
        };

        String userPhone = updateUserRequest.getUserPhone();
        if(userPhone.isEmpty()){
            userPhone = user.getUserPhone();
        }

        UserGender userGender = user.getUserGender();
        if(!updateUserRequest.getUserGender().isEmpty()){
            userGender = enumGenderValid(updateUserRequest.getUserGender());
        }

        user.updateUser(userFirstName, userLastName, userUserName, userPhone, userGender);
    }

    public void formValidation(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequesetException("유효하지 않은 형식의 값입니다.");
        }
    }

    public UserGender enumGenderValid(String userGender) {
        try {
            return UserGender.valueOf(userGender);
        } catch (Exception e) {
            throw new NotFoundException("존재하지 않는 성별입니다.");
        }
    }

    public User findUser(long userId){
        return userRepository.findById(userId).orElseThrow(()-> new NotFoundException("존재하지 않는 회원입니다."));
    }
}
