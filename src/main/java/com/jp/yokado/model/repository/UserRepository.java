package com.jp.yokado.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jp.yokado.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
}
