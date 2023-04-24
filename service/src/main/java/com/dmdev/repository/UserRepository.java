package com.dmdev.repository;

import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>,
        FilterUserRepository,
        RevisionRepository<User, Integer, Integer> {

    @Query(value = "select u from User u",
            countQuery = "select count(distinct u.username) from User u")
    Page<User> findAllBy(Pageable pageable);

    Optional<User> findByUsername(String username);

    List<User> findByTypeBuilding(TypeBuilding typeBuilding);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete User u " +
            "where u.id = :id")
    void delete(Integer id);
}
