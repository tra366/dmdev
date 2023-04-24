package com.dmdev.dto;

import com.dmdev.entity.Role;
import com.dmdev.validation.UserInfo;
import com.dmdev.validation.group.CreateAction;
import com.dmdev.validation.group.UpdateAction;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@UserInfo(groups = UpdateAction.class)
public class UserCreateEditDto {
    String username;

    @NotBlank(groups = CreateAction.class)
    String rawPassword;

    @Size(min = 3, max = 100)
    String firstname;

    @Size(min = 3, max = 100)
    String lastname;

    Role role;
}
