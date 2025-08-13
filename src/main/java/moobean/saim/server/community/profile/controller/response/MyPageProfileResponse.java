package moobean.saim.server.community.profile.controller.response;

public record MyPageProfileResponse(
        Long userId,
        String name,
        String email,
        Integer postNum,
        Integer followerNum,
        Integer followingNum,
        String introduce
) {
}
