package com.software.mywordbox.domain.auth.user.api;


import com.software.mywordbox.domain.auth.user.impl.User;

import java.util.List;

public interface UserService {
    User save(User user);
    UserDto getById(String id);
    UserDto getMe();
    List<UserDto> getAll();
}
