package moobean.saim.server.community.infrastructure;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.service.port.FollowRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

    private final FollowJpaRepository repository;

    @Override
    public Boolean exist(Long followerId, Long targetUserId) {
        return repository.existsByFollowerIdAndTargetUserId(followerId, targetUserId);
    }
}
