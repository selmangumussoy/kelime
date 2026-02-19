package com.software.mywordbox.domain.auth.user.impl;


import com.software.mywordbox.domain.auth.user.api.UserDto;
import com.software.mywordbox.domain.auth.user.api.UserService;
import com.software.mywordbox.domain.auth.user.api.UserUpdateDto;
import com.software.mywordbox.library.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Transactional
    public User save(User user) {
        user.setPhoneNumber(formatPhoneNumber(user.getPhoneNumber()));
        User saved = repository.save(user);
        return saved;
    }


    private String formatPhoneNumber(String phoneNumber) {
        return "90" + phoneNumber;

    }

    @Override
    public UserDto getById(String id) {
        return repository.findById(id).map(UserMapper::toDto)
                .orElseThrow();
    }

    @Override
    public UserDto getMe() {
        return getById(JwtUtil.extractUserIdAndIfAnonymousThrow());
    }

    @Override
    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }




    public Optional<User> findByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress);
    }

    // UserServiceImpl.java içine eklenecek

    @Override
    @Transactional
    public UserDto update(String id, UserUpdateDto updateDto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(updateDto.getFirstName());
        user.setLastName(updateDto.getLastName());
        user.setPhoneNumber(formatPhoneNumber(updateDto.getPhoneNumber()));
        user.setSchool(updateDto.getSchool());
        user.setTargetLanguage(updateDto.getTargetLanguage());
        user.setAge(updateDto.getAge());

        return UserMapper.toDto(repository.save(user));
    }

}
