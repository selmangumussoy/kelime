package com.software.mywordbox.domain.auth.user.web;


import com.software.mywordbox.domain.auth.user.api.UserMapper;
import com.software.mywordbox.domain.auth.user.api.UserService;
import com.software.mywordbox.domain.auth.user.api.UserUpdateDto;
import com.software.mywordbox.library.rest.BaseController;
import com.software.mywordbox.library.rest.DataResponse;
import com.software.mywordbox.library.rest.Response;
import com.software.mywordbox.library.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserService service;

    @GetMapping("/{id}")
    public Response<UserResponse> getUserById(@PathVariable String id) {
        return respond(UserMapper.toResponse(service.getById(id)));
    }

    @GetMapping("/me")
    public Response<UserResponse> getMe() {
        return respond(UserMapper.toResponse(service.getMe()));
    }



    @GetMapping
    public Response<DataResponse<UserResponse>> getAll() {
        List<UserResponse> responseList = service.getAll().stream()
                .map(UserMapper::toResponse)
                .toList();
        return respond(responseList);
    }



    @PutMapping("/{id}")
    public Response<UserResponse> update(@PathVariable String id, @RequestBody UserUpdateDto updateDto) {
        return respond(UserMapper.toResponse(service.update(id, updateDto)));
    }

    @PutMapping("/me")
    public Response<UserResponse> updateMe(@RequestBody UserUpdateDto updateDto) {
        String currentUserId = JwtUtil.extractUserIdAndIfAnonymousThrow();
        return respond(UserMapper.toResponse(service.update(currentUserId, updateDto)));
    }
}
