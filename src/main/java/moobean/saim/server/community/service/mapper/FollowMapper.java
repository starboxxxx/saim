package moobean.saim.server.community.service.mapper;

import moobean.saim.server.community.controller.response.FollowListResponse;
import moobean.saim.server.community.domain.Follow;

public class FollowMapper {

    public static FollowListResponse toFollowerListResponse(Follow follow) {
        return new FollowListResponse(
                follow.getFollower().getId(),
                follow.getFollower().getName()
        );
    }

    public static FollowListResponse toFollowingListResponse(Follow follow) {
        return new FollowListResponse(
                follow.getTargetUser().getId(),
                follow.getTargetUser().getName()
        );
    }
}
