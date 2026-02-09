package com.software.mywordbox.domain.auth.auth.web;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignUpResponse {
    String token;
}