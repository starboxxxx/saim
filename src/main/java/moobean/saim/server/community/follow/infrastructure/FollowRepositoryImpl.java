package moobean.saim.server.community.follow.infrastructure;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.follow.domain.Follow;
import moobean.saim.server.community.follow.infrastructure.entity.FollowEntity;
import moobean.saim.server.community.follow.service.port.FollowRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

    private final FollowJpaRepository repository;

    @Override
    public Follow find(Long followerId, Long targetUserId) {
        return repository.findByFollowerIdAndTargetUserId(followerId, targetUserId)
                .toModel();
    }

    @Override
    public Boolean exist(Long followerId, Long targetUserId) {
        return repository.existsByFollowerIdAndTargetUserId(followerId, targetUserId);
    }

    @Override
    public void follow(Follow follow) {
        repository.save(FollowEntity.from(follow));
    }

    @Override
    public void dropFollow(Follow follow) {
        repository.delete(FollowEntity.from(follow));
    }

    @Override
    public List<Follow> getFollowers(Long targetUserId) {
        List<FollowEntity> followEntities = repository.findByTargetUserId(targetUserId);

        return followEntities.stream()
                .map(FollowEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Follow> getFollowings(Long followerId) {
        List<FollowEntity> followEntities = repository.findByFollowerId(followerId);

        return followEntities.stream()
                .map(FollowEntity::toModel)
                .collect(Collectors.toList());
    }
}
