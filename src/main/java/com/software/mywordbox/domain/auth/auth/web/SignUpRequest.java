package com.software.mywordbox.domain.auth.auth.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String school;
    private String targetLanguage;
    private Integer age;
}