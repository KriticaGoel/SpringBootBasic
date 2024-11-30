package com.kritica.jpa.repositories;

import com.kritica.jpa.models.SocialUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserRepository extends JpaRepository<SocialUsers, Long> {
}
