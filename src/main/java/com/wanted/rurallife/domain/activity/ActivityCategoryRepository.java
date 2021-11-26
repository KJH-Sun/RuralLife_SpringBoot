package com.wanted.rurallife.domain.activity;

import com.wanted.rurallife.domain.categori.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityCategoryRepository extends JpaRepository<ActivityCategory, Long> {

    List<ActivityCategory> findAllByCategoryIn(List<Category> categories);
}
