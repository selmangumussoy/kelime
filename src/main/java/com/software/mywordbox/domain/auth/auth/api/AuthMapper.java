package com.software.mywordbox.domain.auth.auth.api;

import com.software.mywordbox.domain.auth.auth.web.LoginRequest;
import com.software.mywordbox.domain.auth.auth.web.LoginResponse;
import com.software.mywordbox.domain.auth.auth.web.SignUpRequest;
import com.software.mywordbox.domain.auth.auth.web.SignUpResponse;

public class AuthMapper {

    public static SignUpDto toDto(SignUpRequest signUpRequest) {
        return SignUpDto.builder()
                .emailAddress(signUpRequest.getEmailAddress())
                .password(signUpRequest.getPassword())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .school(signUpRequest.getSchool())
                .targetLanguage(signUpRequest.getTargetLanguage())
                .age(signUpRequest.getAge())
                .build();
    }

    public static LoginDto toDto(LoginRequest request) {
        return LoginDto.builder()
                .emailAddress(request.getEmailAddress())
                .password(request.getPassword())
                .build();
    }

    public static SignUpResponse toResponseSignUp(TokenDto tokenDto) {
        return SignUpResponse.builder()
                .token(tokenDto.getToken())
                .build();
    }

    public static LoginResponse toResponseLoginDto(TokenDto tokenDto) {
        return LoginResponse.builder()
                .token(tokenDto.getToken())
                .build();
    }
}