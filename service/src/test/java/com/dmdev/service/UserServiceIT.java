package com.dmdev.service;

import com.dmdev.dto.UserCreateEditDto;
import com.dmdev.dto.UserReadDto;
import com.dmdev.entity.Role;
import com.dmdev.integration.IntegrationTestBase;
import com.dmdev.mapper.UserCreateEditMapper;
import com.dmdev.mapper.UserReadMapper;
import com.dmdev.repository.UserRepository;
import com.dmdev.util.DataImportTestUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceIT extends IntegrationTestBase {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final UserService userService;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;

    @Test
    @Order(1)
    void findAll() {
        DataImportTestUtil.importData(((Session) entityManager).getSessionFactory());

        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(3);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("PPetrov", user.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "UserCreate",
                "FirstCreate",
                "LastCreate",
                Role.BUILDER
        );

        UserReadDto actualUser = userService.create(userCreateEditDto);

        assertThat(userCreateEditDto.getUsername()).isEqualTo(actualUser.getUsername());
        assertThat(userCreateEditDto.getFirstname()).isEqualTo(actualUser.getFirstname());
        assertThat(userCreateEditDto.getLastname()).isEqualTo(actualUser.getLastname());
        assertThat(userCreateEditDto.getRole()).isSameAs(actualUser.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "UserUpdate",
                "FirstUpdate",
                "LastUpdate",
                Role.BUILDER
        );

        Optional<UserReadDto> actualUser = userService.update(1, userCreateEditDto);

        assertTrue(actualUser.isPresent());
        actualUser.ifPresent(user -> {
                    assertThat(userCreateEditDto.getUsername()).isEqualTo(user.getUsername());
                    assertThat(userCreateEditDto.getFirstname()).isEqualTo(user.getFirstname());
                    assertThat(userCreateEditDto.getLastname()).isEqualTo(user.getLastname());
                    assertThat(userCreateEditDto.getRole()).isSameAs(user.getRole());
                }
        );
    }

    @Test
    void delete() {
        assertThat(userService.delete(-1)).isFalse();
        assertThat(userService.delete(1)).isTrue();
    }

}
