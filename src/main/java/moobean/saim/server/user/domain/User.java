package moobean.saim.server.user.domain;

import lombok.Builder;
import lombok.Getter;
import moobean.saim.server.community.profile.domain.Profile;
import moobean.saim.server.oauth.domain.OAuthUserInfo;
import moobean.saim.server.user.infrastructure.entity.ExerciseLevel;
import moobean.saim.server.user.infrastructure.entity.Trainer;
import moobean.saim.server.user.infrastructure.entity.UserRole;

import java.time.LocalDate;

@Getter
public class User {

    private final Long id;

    private String name;

    private String email;

    private final String phoneNumber;

    private final LocalDate birth;

    private final ExerciseLevel exerciseLevel;

    private final Integer exerciseFrequency;

    private final String socialId;

    private final String provider;

    private final UserRole userRole;

    private final String exerciseMethod;

    private final String exerciseGoals;

    private final String healthIssues;

    private Trainer trainer;

    private final Physical physical;

    private Profile profile;

    @Builder
    public User(Long id, String name, String email,
                String phoneNumber, LocalDate birth,
                ExerciseLevel exerciseLevel, Integer exerciseFrequency,
                String socialId, String provider, UserRole userRole,
                Physical physical, Profile profile, String exerciseMethod,
                String exerciseGoals, String healthIssues,
                Trainer trainer) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.exerciseLevel = exerciseLevel;
        this.exerciseFrequency = exerciseFrequency;
        this.socialId = socialId;
        this.provider = provider;
        this.userRole = userRole;
        this.exerciseMethod = exerciseMethod;
        this.exerciseGoals = exerciseGoals;
        this.healthIssues = healthIssues;
        this.trainer = trainer;
        this.physical = physical;
        this.profile = profile;
    }

    public static User create(OAuthUserInfo userInfo) {
        Physical physical = Physical.create();
        Profile profile = Profile.create();
        User user = User.builder()
                .name(userInfo.getName())
                .email(userInfo.getEmail())
                .birth(userInfo.getBirth())
                .phoneNumber(userInfo.getPhone())
                .socialId(userInfo.getId())
                .provider(userInfo.getProvider())
                .userRole(UserRole.USER)
                .physical(physical)
                .profile(profile)
                .build();

        profile.updateUser(user);
        physical.updateUser(user);

        return user;
    }

    public void updateNameAndEmail(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void updateTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public void updateProfile(Profile profile) {
        this.profile = profile;
    }


}
