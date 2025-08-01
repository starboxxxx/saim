package moobean.saim.server.community.service.port;

public interface FollowRepository {
    Boolean exist(Long followerId, Long targetUserId);
}
