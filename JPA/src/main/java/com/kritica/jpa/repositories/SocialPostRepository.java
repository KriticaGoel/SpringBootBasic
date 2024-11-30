package com.kritica.jpa.repositories;

import com.kritica.jpa.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialPostRepository extends JpaRepository<Post,Long> {
}
