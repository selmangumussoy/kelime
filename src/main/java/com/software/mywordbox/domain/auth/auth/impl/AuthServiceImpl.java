package com.software.mywordbox.domain.auth.auth.impl;

import com.software.mywordbox.domain.auth.auth.api.AuthService;
import com.software.mywordbox.domain.auth.auth.api.LoginDto;
import com.software.mywordbox.domain.auth.auth.api.SignUpDto;
import com.software.mywordbox.domain.auth.auth.api.TokenDto;
import com.software.mywordbox.domain.auth.user.impl.User;
import com.software.mywordbox.domain.auth.user.impl.UserMapper;
import com.software.mywordbox.domain.auth.user.impl.UserServiceImpl;
import com.software.mywordbox.library.security.CustomUserDetails;
import com.software.mywordbox.library.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenDto signUp(SignUpDto dto) {
        // Şifreyi encode et
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userService.save(UserMapper.toEntity(dto));
        UserDetails userDetails = new CustomUserDetails(savedUser);
        String token = jwtUtil.generateToken(userDetails);

        return new TokenDto(token, null, null);
    }

    @Override
    public TokenDto login(LoginDto dto) {
        Optional<User> user = userService.findByEmailAddress(dto.getEmailAddress());

        if (user.isPresent() && passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
            return generateToken(user.get());
        }
        return null;
    }

    private TokenDto generateToken(User user) {
        UserDetails userDetails = new CustomUserDetails(user);
        String token = jwtUtil.generateToken(userDetails);
        return new TokenDto(token, null, null);
    }
}