package com.wanted.rurallife.service;


import com.wanted.rurallife.controller.dto.TownDto.RecommendTownListDto;
import com.wanted.rurallife.controller.dto.TownDto.RecommendTownListDto.RecommendTown;
import com.wanted.rurallife.controller.dto.TownDto.TownInfoDto;
import com.wanted.rurallife.controller.dto.TownDto.TownInfoDto.ActivityInfo;
import com.wanted.rurallife.domain.RoomOption.RoomOption;
import com.wanted.rurallife.domain.RoomOption.RoomOptionRepository;
import com.wanted.rurallife.domain.activity.Activity;
import com.wanted.rurallife.domain.activity.ActivityCategory;
import com.wanted.rurallife.domain.activity.ActivityCategoryRepository;
import com.wanted.rurallife.domain.activity.ActivityRepository;
import com.wanted.rurallife.domain.categori.Category;
import com.wanted.rurallife.domain.town.Town;
import com.wanted.rurallife.domain.town.TownRepository;
import com.wanted.rurallife.domain.volunteer.Volunteer;
import com.wanted.rurallife.domain.volunteer.VolunteerCategory;
import com.wanted.rurallife.domain.volunteer.VolunteerRepository;
import com.wanted.rurallife.exception.town.TownNotFoundException;
import com.wanted.rurallife.exception.user.UserNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TownService {

    private final VolunteerRepository volunteerRepository;
    private final ActivityRepository activityRepository;
    private final TownRepository townRepository;
    private final ActivityCategoryRepository activityCategoryRepository;
    private final RoomOptionRepository roomOptionRepository;

    public RecommendTownListDto recommendTown(String tel) {
        Volunteer volunteer = volunteerRepository.findByTel(tel)
            .orElseThrow(() -> new UserNotFoundException("해당 전화번호를 찾을 수 없습니다."));
        List<Category> categoryList = new ArrayList<>();

        for (VolunteerCategory volunteerCategory : volunteer.getPreferActivitys()) {
            categoryList.add(volunteerCategory.getActivityCategory());
        }
        List<ActivityCategory> activityCategories = activityCategoryRepository
            .findAllByCategoryIn(categoryList);

        Map<Town, List<String>> towns = new HashMap<>();
        List<RecommendTown> recommendTowns = new ArrayList<>();
        for (ActivityCategory ac : activityCategories) {
            Town t = ac.getActivity().getTown();
            towns.computeIfAbsent(t, (e) -> new ArrayList<>());
            towns.get(t).add(ac.getCategory().getName());


        }
        for (ActivityCategory ac : activityCategories) {
            Town t = ac.getActivity().getTown();
            recommendTowns.add(RecommendTown.builder()
                .id(t.getId())
                .address_do(t.getAddress().split(" ")[0])
                .categorys(towns.get(t))
                .name(t.getName())
                .build());
        }

        return RecommendTownListDto.builder()
            .towns(recommendTowns)
            .build();
    }

    public TownInfoDto getTownInfo(Long id) {
        Town town = townRepository.findById(id)
            .orElseThrow(() -> new TownNotFoundException("해당 아이디의 마을을 찾을 수 없습니다."));

        List<ActivityInfo> activityInfos = new ArrayList<>();
        List<Activity> activities = activityRepository.findAllByTown(town);
        for (Activity activity : activities) {
            activityInfos.add(ActivityInfo.builder()
                .name(activity.getName())
                .activityTime(activity.getActivityTime())
                .build());
        }
        List<String> roomOptionNames = roomOptionRepository.findAllByTown(town).stream()
            .map(RoomOption::getName).collect(
                Collectors.toList());

        return TownInfoDto.builder()
            .id(town.getId())
            .name(town.getName())
            .address(town.getAddress())
            .address_rooms(town.getAddress_rooms())
            .contact(town.getContact())
            .roomOptions(roomOptionNames)
            .description(town.getDescription())
            .start_date(town.getStartDate())
            .end_date(town.getEndDate())
            .activityInfos(activityInfos)
            .build();
    }

}
