package com.dmdev.repository;

import com.dmdev.entity.TypeBuilding;
import com.dmdev.entity.User;

import java.util.List;

public interface FilterUserRepository {

    List<User> findByTypeBuilding(TypeBuilding typeBuilding);
}
