package com.cts.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.user.entity.Idp;

@Repository
public interface IdpRepository extends JpaRepository<Idp, Long> {
}
