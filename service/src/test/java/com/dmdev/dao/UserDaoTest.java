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

public class UserDaoTest extends IntegrationTestBase {

    @Test
    void save() {
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser1");
        userRepository.save(user);
        getSession().clear();

        User actualUser = getSession().get(User.class, user.getId());

        assertThat(actualUser.getId()).isNotNull();
    }

    @Test
    void update() {
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("APetrov");
        userRepository.save(user);
        user.setLastName("NewAPetrov");
        userRepository.update(user);
        getSession().flush();
        getSession().clear();

        User actualUser = getSession().get(User.class, user.getId());

        assertThat(actualUser.getLastName()).isEqualTo("NewAPetrov");
    }

    @Test
    void findById() {
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser2");
        userRepository.save(user);
        getSession().clear();

        Optional<User> findChart = userRepository.findById(user.getId());

        assertThat(findChart).isNotNull();
    }

    @Test
    void delete() {
        UserRepository userRepository = new UserRepository(getSession());
        User user = GetEntity.getUser("UUser3");
        userRepository.save(user);
        userRepository.delete(user);
        getSession().clear();

        User actualUser = getSession().get(User.class, user.getId());

        assertThat(actualUser).isNull();
    }

    @Test
    void getAll() {
        UserRepository userRepository = new UserRepository(getSession());
        List<User> results = userRepository.getAll();

        assertThat(results).hasSize(3);

        List<String> userNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(userNames).containsExactlyInAnyOrder("PPetrov", "IIvanov", "SSidorov");
    }

    @Test
    void getByUsername() {
        UserRepository userRepository = new UserRepository(getSession());
        List<User> results = userRepository.getByUsername("PPetrov");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("PPetrov");
    }

    @Test
    void getByTypeBuilding() {
        UserRepository userRepository = new UserRepository(getSession());
        List<User> results = userRepository.getByTypeBuilding(TypeBuilding.GF_RT_CS);

        assertThat(results).hasSize(2);

        List<String> userNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(userNames).containsExactlyInAnyOrder("PPetrov", "IIvanov");
    }
}
