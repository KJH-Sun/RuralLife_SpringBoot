package com.wanted.rurallife.domain.RoomOption;

import com.wanted.rurallife.domain.activity.Activity;
import com.wanted.rurallife.domain.town.Town;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomOptionRepository extends JpaRepository<RoomOption, Long> {

    List<RoomOption> findAllByTown(Town town);
}
