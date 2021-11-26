package com.wanted.rurallife.controller.dto;

import com.wanted.rurallife.domain.volunteer.Volunteer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class VolunteerDto {


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpDto {

        private String name;
        private String birth;
        private String tel;
        private List<String> prefers;

        @Builder
        public SignUpDto(String name, String birth, String tel, List<String> prefers) {
            this.name = name;
            this.birth = birth;
            this.tel = tel;
            this.prefers = prefers;
        }

        @Override
        public String toString() {
            return "SignUpDto{" +
                "name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", tel='" + tel + '\'' +
                ", prefers=" + prefers +
                '}';
        }

        public Volunteer toEntity(String password) {
            return Volunteer.builder()
                .name(name)
                .birth(LocalDate.parse(birth, DateTimeFormatter.ISO_DATE))
                .tel(tel)
                .password(password)
                .role("ROLE_VOLUNTEER")
                .build();
        }
    }

}
