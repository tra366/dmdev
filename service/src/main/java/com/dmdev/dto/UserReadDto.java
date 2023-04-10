package com.dmdev.dto;

import com.dmdev.entity.Role;
import lombok.Value;

@Value
public class UserReadDto extends BaseUser {
    Integer id;
    String username;
    String firstname;
    String lastname;
    Role role;
}
