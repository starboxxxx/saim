package moobean.saim.server.community.infrastructure;

import moobean.saim.server.community.infrastructure.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<FollowEntity, Long> {

    Boolean existsByFollowerIdAndTargetUserId(Long followerId, Long targetUserId);
}
