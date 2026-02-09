package com.software.mywordbox.domain.auth.auth.api;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginDto {
    private String emailAddress;
    private String password;
}