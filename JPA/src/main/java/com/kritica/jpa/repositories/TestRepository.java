package com.kritica.jpa.repositories;

import com.kritica.jpa.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
