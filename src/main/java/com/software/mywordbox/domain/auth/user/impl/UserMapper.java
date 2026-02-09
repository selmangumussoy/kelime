package com.software.mywordbox.domain.auth.user.impl;

import com.software.mywordbox.domain.auth.auth.api.SignUpDto;
import com.software.mywordbox.domain.auth.user.api.UserDto;

public class UserMapper {

    public static User toEntity(SignUpDto dto) {
        User user = new User();
        user.setPassword(dto.getPassword());
        user.setEmailAddress(dto.getEmailAddress());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setSchool(dto.getSchool());
        user.setTargetLanguage(dto.getTargetLanguage());
        user.setAge(dto.getAge());
        return user;
    }

    public static User toEntityForUser(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        user.setEmailAddress(dto.getEmailAddress());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setSchool(dto.getSchool());
        user.setTargetLanguage(dto.getTargetLanguage());
        user.setAge(dto.getAge());
        user.setCreated(dto.getCreated());
        user.setModified(dto.getModified());
        return user;
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .password(user.getPassword())
                .emailAddress(user.getEmailAddress())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .school(user.getSchool())
                .targetLanguage(user.getTargetLanguage())
                .age(user.getAge())
                .build();
    }
}