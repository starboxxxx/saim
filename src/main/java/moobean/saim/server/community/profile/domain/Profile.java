package moobean.saim.server.community.profile.domain;

import lombok.Builder;
import lombok.Getter;
import moobean.saim.server.user.domain.User;

@Getter
public class Profile {

    private final Long id;

    private String introduce;

    private final Boolean showAbility;

    private final Integer postNum;

    private Integer followerNum;

    private Integer followingNum;

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

    public void plusFollowerNum() {
        this.followerNum += 1;
    }

    public void plusFollowingNum() {
        this.followingNum += 1;
    }

    public void minusFollowerNum() {
        this.followerNum -= 1;
    }

    public void minusFollowingNum() {
        this.followingNum -= 1;
    }
}
