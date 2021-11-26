package com.wanted.rurallife.domain.activity;

import com.wanted.rurallife.domain.categori.Category;
import com.wanted.rurallife.domain.town.Town;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByTown(Town town);
}
