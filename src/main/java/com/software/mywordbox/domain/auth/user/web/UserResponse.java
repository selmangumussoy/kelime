package com.software.mywordbox.domain.auth.user.web;

import com.software.mywordbox.library.abstraction.AbstractResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends AbstractResponse {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String school;
    private String targetLanguage;
    private Integer age;
}