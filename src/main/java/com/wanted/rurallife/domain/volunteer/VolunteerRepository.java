package com.wanted.rurallife.domain.volunteer;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Optional<Volunteer> findByTel(String tel);

    Optional<Volunteer> findOneWithAuthoritiesByTel(String tel);
}
