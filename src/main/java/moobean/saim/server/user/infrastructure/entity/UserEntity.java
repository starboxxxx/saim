package moobean.saim.server.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import moobean.saim.server.community.infrastructure.entity.FollowEntity;
import moobean.saim.server.community.infrastructure.entity.ProfileEntity;
import moobean.saim.server.global.BaseTimeEntity;
import moobean.saim.server.user.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "USER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String phoneNumber;

    private LocalDate birth;

    private ExerciseLevel exerciseLevel;

    private Integer exerciseFrequency;

    @Column(nullable = false)
    private String socialId;

    @Column(nullable = false)
    private String provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="physical_id")
    private PhysicalEntity physical;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", unique = true) // 외래키 컬럼이 USER 테이블에 생김
    private ProfileEntity profile;

    private String exerciseMethod; // 운동방법

    private String exerciseGoals; // 운동 목표

    private String healthIssues; // 건강 이슈

    @Enumerated(EnumType.STRING)
    private Trainer trainer; // 트레이너 선생님

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<FollowEntity> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "targetUser", cascade = CascadeType.ALL)
    private List<FollowEntity> followerList = new ArrayList<>();

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
        userEntity.name = user.getName();
        userEntity.email = user.getEmail();
        userEntity.phoneNumber = user.getPhoneNumber();
        userEntity.birth = user.getBirth();
        userEntity.phoneNumber = user.getPhoneNumber();
        userEntity.exerciseLevel = user.getExerciseLevel();
        userEntity.exerciseFrequency = user.getExerciseFrequency();
        userEntity.socialId = user.getSocialId();
        userEntity.provider = user.getProvider();
        userEntity.userRole = user.getUserRole();

        userEntity.exerciseMethod = user.getExerciseMethod();
        userEntity.exerciseGoals = user.getExerciseGoals();
        userEntity.healthIssues = user.getHealthIssues();

        userEntity.trainer = user.getTrainer();

        userEntity.physical = Optional.ofNullable(user.getPhysical())
                .map(PhysicalEntity::from)
                .orElse(null);

        userEntity.profile = Optional.ofNullable(user.getProfile())
                .map(ProfileEntity::from)
                .orElse(null);
        return userEntity;
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .birth(birth)
                .exerciseLevel(exerciseLevel)
                .exerciseFrequency(exerciseFrequency)
                .socialId(socialId)
                .provider(provider)
                .userRole(userRole)
                .exerciseMethod(exerciseMethod)
                .exerciseGoals(exerciseGoals)
                .healthIssues(healthIssues)
                .trainer(trainer)
                .physical(physical != null ? physical.toModel() : null)
                .profile(profile != null ? profile.toModel() : null)
                .build();
    }

}
