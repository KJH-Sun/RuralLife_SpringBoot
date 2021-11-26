package com.wanted.rurallife.service;

import com.wanted.rurallife.domain.UserRepository;
import com.wanted.rurallife.domain.common.UserBase;
import com.wanted.rurallife.domain.volunteer.VolunteerRepository;
import com.wanted.rurallife.exception.user.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDetailsService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final VolunteerRepository volunteerRepository;

    // 해당 전화번호가 존재하는지 확인
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String tel) {
        UserBase result;
        result = volunteerRepository.findByTel(tel)
            .orElseThrow(() -> new UserNotFoundException("해당하는 전화번호가 존재하지 않습니다."));

        return createUser(tel, result);
    }

    // 해당 이메일로 인가된 객체 생성
    private org.springframework.security.core.userdetails.User createUser(String tel,
        UserBase userBase) {

        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority
            .add(new SimpleGrantedAuthority("Volunteer"));
        return new org.springframework.security.core.userdetails.User(tel,
            userBase.getPassword(),
            grantedAuthority);
    }
}
