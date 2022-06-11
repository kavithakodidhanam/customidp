package com.cts.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
