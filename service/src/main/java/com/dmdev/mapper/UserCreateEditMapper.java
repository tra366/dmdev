package com.dmdev.mapper;

import com.dmdev.dto.UserCreateEditDto;
import com.dmdev.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final PasswordEncoder passwordEncoder;

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

        Optional.ofNullable(userDto.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);

        return user;
    }
}
