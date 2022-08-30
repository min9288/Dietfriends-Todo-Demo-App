package com.DietfriendsTodoDemoApp.domain.user.entity;

import com.DietfriendsTodoDemoApp.domain.user.entity.enumPackage.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User {

    // 유저 PK
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userUUID;

    // 유저 name
    @Column(nullable = false, length = 40)
    private String userName;

    // 유저 password
    @Column(nullable = false, length = 100)
    @JsonIgnore
    private String password;

    // 유저 나이
    @Column(nullable = false)
    @Max(value = 150)
    @Min(value = 0)
    private Integer age;

    // 가입일
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(nullable = false, updatable = false)
    private LocalDate userEnrollDate;

    // 재발행 토큰
    private String refreshToken;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    @Builder
    public User(String userName, String password, Integer age,List<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.roles = Collections.singletonList(Role.ROLE_MEMBER);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
