package moobean.saim.server.community.controller.port;

public interface FollowService {
    Boolean checkFollow(Long followerId, Long targetUserId);
}
