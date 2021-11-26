package com.wanted.rurallife.domain;

import com.wanted.rurallife.domain.common.UserBase;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserBase, Long> {

    Optional<UserBase> findByTel(String tel);

    boolean existsByTel(String tel);
}
