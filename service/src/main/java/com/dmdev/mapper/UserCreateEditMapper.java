package com.dmdev.mapper;

import com.dmdev.dto.UserCreateEditDto;
import com.dmdev.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    @Override
    public User map(UserCreateEditDto userDto) {
        User user = new User();
        return map(userDto, user);
    }

    @Override
    public User map(UserCreateEditDto userDto, User user) {
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstname());
        user.setLastName(userDto.getLastname());
        user.setRole(userDto.getRole());
        return user;
    }
}
