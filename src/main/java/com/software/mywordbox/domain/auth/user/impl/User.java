package com.software.mywordbox.domain.auth.user.impl;

import jakarta.persistence.*;
import com.software.mywordbox.library.rest.AbstractEntity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = User.TABLE)
public class User extends AbstractEntity {
    public static final String TABLE = "users";
    private static final String COL_PASSWORD = "password";
    private static final String COL_MAIL_ADDRESS = "email_address";
    private static final String COL_FIRST_NAME = "first_name";
    private static final String COL_LAST_NAME = "last_name";
    private static final String COL_PHONE_NUMBER = "phone_number";
    private static final String COL_SCHOOL = "school";
    private static final String COL_TARGET_LANGUAGE = "target_language";
    private static final String COL_AGE = "age";
    // private static final String COL_ROLE = "role";

    @Column(name = COL_PASSWORD, nullable = false)
    private String password;

    @Column(name = COL_MAIL_ADDRESS, unique = true)
    private String emailAddress;

    @Column(name = COL_FIRST_NAME)
    private String firstName;

    @Column(name = COL_LAST_NAME)
    private String lastName;

    @Column(name = COL_PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = COL_SCHOOL)
    private String school;

    @Column(name = COL_TARGET_LANGUAGE)
    private String targetLanguage;

    @Column(name = COL_AGE)
    private Integer age;

//    @Enumerated(EnumType.STRING)
//    @Column(name = COL_ROLE, nullable = false)
//    private Role role;
}