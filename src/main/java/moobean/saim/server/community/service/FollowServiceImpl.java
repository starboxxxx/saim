package moobean.saim.server.community.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.controller.port.FollowService;
import moobean.saim.server.community.service.port.FollowRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

    @Override
    public Boolean checkFollow(Long followerId, Long targetUserId) {
        return followRepository.exist(followerId, targetUserId);
    }
}
