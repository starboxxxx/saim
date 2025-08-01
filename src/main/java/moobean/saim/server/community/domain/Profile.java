package moobean.saim.server.community.domain;

import lombok.Builder;
import lombok.Getter;
import moobean.saim.server.user.domain.User;

@Getter
public class Profile {

    private final Long id;

    private String introduce;

    private final Boolean showAbility;

    private final Integer postNum;

    private final Integer followerNum;

    private final Integer followingNum;

    private User user;

    @Builder
    public Profile(Long id, String introduce,
                   boolean showAbility, Integer postNum,
                   Integer followerNum, Integer followingNum) {
        this.id = id;
        this.introduce = introduce;
        this.showAbility = showAbility;
        this.postNum = postNum;
        this.followerNum = followerNum;
        this.followingNum = followingNum;
    }

    public static Profile create() {
        return Profile.builder()
                .introduce("")
                .showAbility(false)
                .postNum(0)
                .followerNum(0)
                .followingNum(0)
                .build();
    }

    public void updateUser(User user) {
        this.user = user;
    }

    public void updateIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
