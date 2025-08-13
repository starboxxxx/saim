package moobean.saim.server.community.follow.infrastructure;

import moobean.saim.server.community.follow.infrastructure.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowJpaRepository extends JpaRepository<FollowEntity, Long> {

    Boolean existsByFollowerIdAndTargetUserId(Long followerId, Long targetUserId);

    @Query("SELECT f FROM FollowEntity f WHERE f.targetUser.id = :targetUserId")
    List<FollowEntity> findByTargetUserId(@Param("targetUserId") Long targetUserId);

    @Query("SELECT f FROM FollowEntity f WHERE f.follower.id = :followerId")
    List<FollowEntity> findByFollowerId(@Param("followerId") Long followerId);

    FollowEntity findByFollowerIdAndTargetUserId(Long followerId, Long targetUserId);
}
