package com.wanted.rurallife.service;


import com.wanted.rurallife.controller.dto.VolunteerDto.SignUpDto;
import com.wanted.rurallife.domain.categori.Category;
import com.wanted.rurallife.domain.categori.CategoryRepository;
import com.wanted.rurallife.domain.volunteer.Volunteer;
import com.wanted.rurallife.domain.volunteer.VolunteerCategoryRepository;
import com.wanted.rurallife.domain.volunteer.VolunteerRepository;
import com.wanted.rurallife.exception.category.CategoryNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final CategoryRepository activityCategoryRepository;
    private final VolunteerCategoryRepository volunteerActivityCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpDto signUpDto) {
        log.info(signUpDto.toString());
        Volunteer volunteer = signUpDto.toEntity(passwordEncoder.encode("1"));
        for (String activity : signUpDto.getPrefers()) {
            Category activityCategory = activityCategoryRepository.findByName(activity)
                .orElseThrow(() -> new CategoryNotFoundException("해당 봉사활동 카테고리가 없습니다."));
            volunteer.addPreferActivity(activityCategory);
        }
        volunteerRepository.save(volunteer);
    }
}
