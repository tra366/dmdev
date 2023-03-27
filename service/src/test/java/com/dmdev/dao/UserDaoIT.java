package com.dmdev.dao;

import com.dmdev.GetEntity;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class UserDaoIT extends IntegrationTestBase {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Test
    void save() {
        User user = GetEntity.getUser("UUser1");
        userRepository.save(user);
        entityManager.clear();

        User actualUser = ((Session) entityManager).get(User.class, user.getId());

        assertThat(actualUser.getId()).isNotNull();
    }

    @Test
    void update() {
        User user = GetEntity.getUser("APetrov");
        userRepository.save(user);
        user.setLastName("NewAPetrov");
        userRepository.update(user);
        entityManager.clear();

        User actualUser = entityManager.find(User.class, user.getId());

        assertThat(actualUser.getLastName()).isEqualTo("NewAPetrov");
    }

    @Test
    void findById() {
        User user = GetEntity.getUser("UUser2");
        userRepository.save(user);
        entityManager.clear();

        Optional<User> findChart = userRepository.findById(user.getId());

        assertThat(findChart).isNotNull();
    }

    @Test
    void delete() {
        User user = GetEntity.getUser("UUser3");
        userRepository.save(user);
        userRepository.delete(user);
        entityManager.clear();

        User actualUser = entityManager.find(User.class, user.getId());

        assertThat(actualUser).isNull();
    }

    @Test
    void getAll() {
        List<User> results = userRepository.getAll();

        assertThat(results).hasSize(3);

        List<String> userNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(userNames).containsExactlyInAnyOrder("PPetrov", "IIvanov", "SSidorov");
    }

    @Test
    void getByUsername() {
        List<User> results = userRepository.getByUsername("PPetrov");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("PPetrov");
    }

    @Test
    void getByTypeBuilding() {
        List<User> results = userRepository.getByTypeBuilding(TypeBuilding.GF_RT_CS);

        assertThat(results).hasSize(2);

        List<String> userNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(userNames).containsExactlyInAnyOrder("PPetrov", "IIvanov");
    }
}
