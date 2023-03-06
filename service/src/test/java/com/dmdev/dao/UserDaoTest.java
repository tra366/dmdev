package com.dmdev.dao;

import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class UserDaoTest extends IntegrationTestBase {

    @Test
    void getAll() {
        List<User> results = myDao.getAll(session);

        assertThat(results).hasSize(3);

        List<String> userNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(userNames).containsExactlyInAnyOrder("PPetrov", "IIvanov", "SSidorov");
    }

    @Test
    void getByUsername() {
        List<User> results = myDao.getByUsername(session, "PPetrov");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("PPetrov");
    }

    @Test
    void getByTypeBuilding() {
        List<User> results = myDao.getByTypeBuilding(session, "GF_RT_CS");

        assertThat(results).hasSize(2);

        List<String> userNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(userNames).containsExactlyInAnyOrder("PPetrov", "IIvanov");
    }
}
