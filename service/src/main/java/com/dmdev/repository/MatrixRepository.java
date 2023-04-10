package com.dmdev.repository;

import com.dmdev.entity.Matrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrixRepository extends JpaRepository<Matrix, Integer> {
}
