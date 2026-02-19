package com.software.mywordbox.domain.auth.user.api;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String school;
    private String targetLanguage;
    private Integer age;
}