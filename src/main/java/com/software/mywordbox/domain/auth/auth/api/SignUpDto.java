package com.software.mywordbox.domain.auth.auth.api;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignUpDto {
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String school;
    private String targetLanguage;
    private Integer age;
}