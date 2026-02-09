package com.software.mywordbox.domain.auth.auth.api;

public interface AuthService {
    TokenDto signUp(SignUpDto dto);

    TokenDto login(LoginDto dto);
}