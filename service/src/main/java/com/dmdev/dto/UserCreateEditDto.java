package com.dmdev.dto;

import com.dmdev.entity.Role;
import lombok.Value;

@Value
public class UserCreateEditDto extends BaseUser {
    String username;
    String firstname;
    String lastname;
    Role role;
}
