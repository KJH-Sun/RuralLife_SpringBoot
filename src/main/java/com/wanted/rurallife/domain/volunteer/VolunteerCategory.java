package com.wanted.rurallife.domain.volunteer;


import com.wanted.rurallife.domain.categori.Category;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "volunteer_prefer_categori",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"volunteer_id", "category_id"}
        )
    }
)
public class VolunteerCategory {

    @Builder
    public VolunteerCategory(Long id, Volunteer volunteer,
        Category activityCategory) {
        this.id = id;
        this.volunteer = volunteer;
        this.activityCategory = activityCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_prefer_category_id")
    private Long id; // 대리 키

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer; // 학생

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category activityCategory; // 센세

}
