package com.software.mywordbox.domain.auth.user.impl;


import com.software.mywordbox.domain.auth.user.api.UserDto;
import com.software.mywordbox.domain.auth.user.api.UserService;
import com.software.mywordbox.library.security.JwtUtil;
import com.software.mywordbox.library.utils.Functions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user) {
        user.setPhoneNumber(formatPhoneNumber(user.getPhoneNumber()));
        User saved = repository.save(user);
        return saved;
    }

//    public UserDto save(UserDto userDto) {
//        String password = StringUtils.hasLength(userDto.getPassword())
//                ? userDto.getPassword()
//                : Functions.generateRandomPassword();
//        userDto.setPassword(passwordEncoder.encode(password));
//        return UserMapper.toDto(repository.save(UserMapper.toEntityForUser(userDto)));
//    }

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



//    public Optional<User> findByUserNameAndRole(String username, Role role) {
//        return repository.findByUserNameAndRole(username,role);
//
//    }

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


}
