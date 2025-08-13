package moobean.saim.server.community.profile.controller.response;

public record ProfileResponse(
        Long userId,
        Boolean isSame,
        Boolean isFollowed,
        Integer postNum,
        Integer followerNum,
        Integer followingNum,
        String introduce
) {
}
