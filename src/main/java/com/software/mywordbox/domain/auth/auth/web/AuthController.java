package com.software.mywordbox.domain.auth.auth.web;

import com.software.mywordbox.domain.auth.auth.api.AuthMapper;
import com.software.mywordbox.domain.auth.auth.api.AuthService;
import com.software.mywordbox.library.rest.BaseController;
import com.software.mywordbox.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public Response<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return respond(AuthMapper.toResponseSignUp(authService.signUp(AuthMapper.toDto(signUpRequest))));
    }

    @PostMapping("/login")
    public Response<LoginResponse> login(@RequestBody LoginRequest request) {
        return respond(AuthMapper.toResponseLoginDto(authService.login(AuthMapper.toDto(request))));
    }




}