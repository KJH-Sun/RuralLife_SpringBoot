package com.wanted.rurallife.domain.volunteer;


import com.wanted.rurallife.domain.categori.Category;
import com.wanted.rurallife.domain.common.UserBase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Table(name = "user_volunteer")
@DiscriminatorValue("Volunteer")
@PrimaryKeyJoinColumn(name = "volunteer_id")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Volunteer extends UserBase {

    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL)
    private List<VolunteerCategory> preferActivitys = new ArrayList<>();

    public void addPreferActivity(Category activityCategori) {
        if (preferActivitys == null) {
            preferActivitys = new ArrayList<>();
        }
        preferActivitys.add(VolunteerCategory.builder()
            .volunteer(this)
            .activityCategory(activityCategori)
            .build());
    }
}
