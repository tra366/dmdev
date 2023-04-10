package com.dmdev.repository;

import com.dmdev.GetEntity;
import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import com.dmdev.util.DataImportTestUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoIT extends IntegrationTestBase {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Test
    @Order(1)
    void save() {
        DataImportTestUtil.importData(((Session) entityManager).getSessionFactory());

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
        userRepository.save(user);
        entityManager.flush();
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
        userRepository.delete(user.getId());
        entityManager.clear();

        User actualUser = entityManager.find(User.class, user.getId());

        assertThat(actualUser).isNull();
    }

    @Test
    void getAll() {
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var results = userRepository.findAllBy(pageable);

        assertThat(results).hasSize(2);

        List<String> userNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(userNames).containsExactlyInAnyOrder("PPetrov", "IIvanov");
    }

    @Test
    void getByUsername() {
        User petrov = GetEntity.getUser("APetrov");
        userRepository.save(petrov);

        List<User> results = userRepository.findByUsername("APetrov");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("APetrov");
    }

    @Test
    void getByTypeBuilding() {
        List<User> results = userRepository.findByTypeBuilding(TypeBuilding.GF_RT_CS);

        assertThat(results).hasSize(2);

        List<String> userNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(userNames).containsExactlyInAnyOrder("PPetrov", "IIvanov");
    }
}
