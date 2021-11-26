package com.wanted.rurallife.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wanted.rurallife.domain.BaseTimeEntity;
import io.jsonwebtoken.Claims;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user_base")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "role")
public class UserBase extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // 대리 키

    private String name;

    private LocalDate birth;

    private String tel;

    private String password;

    @JsonIgnore
    @Transient
    private String role;

    public UserBase(Claims claims) {
        this.name = claims.get("name").toString();
        this.tel = claims.get("tel").toString();
        this.role = claims.get("role").toString();
    }

}
