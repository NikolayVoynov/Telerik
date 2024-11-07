package com.telerikacademy.beertag.helpers;

import com.telerikacademy.beertag.models.RegisterDto;
import com.telerikacademy.beertag.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User dtoToUser(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());

        return user;
    }
}
