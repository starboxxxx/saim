package moobean.saim.server.community.follow.controller.port;

import moobean.saim.server.community.follow.controller.response.FollowListResponse;

import java.util.List;

public interface FindFollowService {

    List<FollowListResponse> getFollowers(Long targetUserId);

    List<FollowListResponse> getFollowings(Long followerId);
}
