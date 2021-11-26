package com.wanted.rurallife.domain.town;


import com.wanted.rurallife.domain.RoomOption.RoomOption;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "town")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id")
    private Long id;

    private String name;

    private String description;

    private String address;

    private String address_rooms;

    private String contact;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "town", cascade = CascadeType.ALL)
    private List<RoomOption> roomOptions;

}
