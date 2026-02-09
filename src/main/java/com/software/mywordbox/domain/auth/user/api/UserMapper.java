package com.software.mywordbox.domain.auth.user.api;

import com.software.mywordbox.domain.auth.user.web.UserResponse;

public class UserMapper {
    public static UserResponse toResponse(UserDto userDto) {
        return UserResponse.builder()
                .id(userDto.getId())
                .created(userDto.getCreated())
                .modified(userDto.getModified())
                .emailAddress(userDto.getEmailAddress())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .phoneNumber(userDto.getPhoneNumber())
                .school(userDto.getSchool())
                .targetLanguage(userDto.getTargetLanguage())
                .age(userDto.getAge())
                .build();
    }
}