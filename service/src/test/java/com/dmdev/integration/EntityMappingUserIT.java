package com.dmdev.integration;

import com.dmdev.GetEntity;
import com.dmdev.entity.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityMappingUserIT extends IntegrationTestBase {

    private Session session = getSession();

    @Test
    void addUser() {
        User newUser = GetEntity.getUser();
        session.save(newUser);
        session.clear();

        User actualUser = session.get(User.class, newUser.getId());

        assertThat(actualUser.getId()).isNotNull();
    }

    @Test
    void getUser() {
        User newUser = GetEntity.getUser("UUser");
        session.save(newUser);
        session.clear();

        User actualUser = session.get(User.class, newUser.getId());

        assertThat(actualUser).isEqualTo(newUser);
    }

    @Test
    void updateUser() {
        User newUser = GetEntity.getUser("APetrov");
        session.save(newUser);
        newUser.setLastName("NewAPetrov");
        session.flush();
        session.clear();

        User actualUser = session.get(User.class, newUser.getId());

        assertThat(actualUser.getLastName()).isEqualTo("NewAPetrov");
    }

    @Test
    void deleteUser() {
        User newUser = GetEntity.getUser("VSidorov");
        session.save(newUser);
        session.delete(newUser);
        session.flush();
        session.clear();

        User actualUser = session.get(User.class, newUser.getId());

        assertThat(actualUser).isNull();
    }

}
