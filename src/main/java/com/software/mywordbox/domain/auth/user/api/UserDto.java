package com.software.mywordbox.domain.auth.user.api;

import com.software.mywordbox.library.abstraction.AbstractDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AbstractDto {
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String school;
    private String targetLanguage;
    private Integer age;
}