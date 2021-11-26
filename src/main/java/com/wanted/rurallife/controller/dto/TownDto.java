package com.wanted.rurallife.controller.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TownDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RecommendTownListDto {

        private List<RecommendTown> towns;

        @Builder
        public RecommendTownListDto(List<RecommendTown> towns) {
            this.towns = towns;
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class RecommendTown {

            private Long id;
            private String name;
            private String address_do;
            private List<String> categorys;

            @Builder
            public RecommendTown(Long id, String name, String address_do,
                List<String> categorys) {
                this.id = id;
                this.name = name;
                this.address_do = address_do;
                this.categorys = categorys;
            }
        }
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TownInfoDto {

        private Long id;
        private String name;
        private String address;
        private String address_rooms;
        private String contact;
        private List<String> roomOptions;
        private String description;
        private LocalDate start_date;
        private LocalDate end_date;
        private List<ActivityInfo> activityInfos;

        @Builder
        public TownInfoDto(Long id, String name, String address, String address_rooms,
            String contact, List<String> roomOptions, String description, LocalDate start_date,
            LocalDate end_date,
            List<ActivityInfo> activityInfos) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.address_rooms = address_rooms;
            this.contact = contact;
            this.roomOptions = roomOptions;
            this.description = description;
            this.start_date = start_date;
            this.end_date = end_date;
            this.activityInfos = activityInfos;
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class ActivityInfo {

            String name;
            int activityTime;

            @Builder
            public ActivityInfo(String name, int activityTime) {
                this.name = name;
                this.activityTime = activityTime;
            }
        }

    }


}
