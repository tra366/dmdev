package com.dmdev.dao;

import com.dmdev.GetEntity;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest extends IntegrationTestBase {


    @Test
    void save() {
        User user = GetEntity.getUser("UUser1");
        getUserRepository().save(user);
        getSession().clear();

        User actualUser = getSession().get(User.class, user.getId());

        assertThat(actualUser.getId()).isNotNull();
    }

    @Test
    void update() {
        User user = GetEntity.getUser("APetrov");
        getUserRepository().save(user);
        user.setLastName("NewAPetrov");
        getUserRepository().update(user);
        getSession().clear();

        User actualUser = getSession().get(User.class, user.getId());

        assertThat(actualUser.getLastName()).isEqualTo("NewAPetrov");
    }

    @Test
    void findById() {
        User user = GetEntity.getUser("UUser2");
        getUserRepository().save(user);
        getSession().clear();

        Optional<User> findChart = getUserRepository().findById(user.getId());

        assertThat(findChart).isNotNull();
    }

    @Test
    void delete() {
        User user = GetEntity.getUser("UUser3");
        getUserRepository().save(user);
        getUserRepository().delete(user);
        getSession().clear();

        User actualUser = getSession().get(User.class, user.getId());

        assertThat(actualUser).isNull();
    }

    @Test
    void getAll() {
        List<User> results = getUserRepository().getAll();

        assertThat(results).hasSize(3);

        List<String> userNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(userNames).containsExactlyInAnyOrder("PPetrov", "IIvanov", "SSidorov");
    }

    @Test
    void getByUsername() {
        List<User> results = getUserRepository().getByUsername("PPetrov");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("PPetrov");
    }

    @Test
    void getByTypeBuilding() {
        List<User> results = getUserRepository().getByTypeBuilding(TypeBuilding.GF_RT_CS);

        assertThat(results).hasSize(2);

        List<String> userNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(userNames).containsExactlyInAnyOrder("PPetrov", "IIvanov");
    }
}
