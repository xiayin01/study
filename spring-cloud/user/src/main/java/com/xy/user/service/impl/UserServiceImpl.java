package com.xy.user.service.impl;

import com.xy.user.dto.UserDto;
import com.xy.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getUser(Long id) {
        return new UserDto(1L, "xy");
    }
}
