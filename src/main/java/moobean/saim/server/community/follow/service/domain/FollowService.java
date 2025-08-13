package moobean.saim.server.community.follow.service.domain;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.follow.service.port.FollowRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public Boolean checkFollow(Long followerId, Long targetUserId) {
        return followRepository.exist(followerId, targetUserId);
    }
}
