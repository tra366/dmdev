package com.dmdev.http.controller.rest;

import com.dmdev.entity.Role;
import com.dmdev.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRestControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;
    private final EntityManager entityManager;

    @Test
    @Order(1)
    void findAll() throws Exception {
        //  Денис, за мной должок с прошлого урока - к следующей домашке переделаю на DML
        //  DataImportTestUtil.importData(((Session) entityManager).getSessionFactory());

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .param("username", "TestUsername")
                        .param("firstname", "TestFirstname")
                        .param("lastname", "TestLastname")
                        .param("role", Role.BUILDER.toString())
                )
                .andExpectAll(
                        status().is2xxSuccessful());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users", "1")
                        .param("username", "TestUsernameUpdate")
                        .param("firstname", "TestFirstnameUpdate")
                        .param("lastname", "TestLastnameUpdate")
                        .param("role", Role.BUILDER.toString())
                )
                .andExpectAll(
                        status().is2xxSuccessful());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/{id}", "3"))
                .andExpectAll(
                        status().is2xxSuccessful());
    }
}
