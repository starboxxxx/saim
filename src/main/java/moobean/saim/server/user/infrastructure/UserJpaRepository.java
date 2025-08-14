package moobean.saim.server.user.infrastructure;

import io.lettuce.core.dynamic.annotation.Param;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberStatus;
import moobean.saim.server.user.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findBySocialId(String socialId);

    @Query("""
        SELECT u FROM UserEntity u
        WHERE 
            u.id NOT IN (
                SELECT m.user.id FROM ClubMemberEntity m
                WHERE m.club.id = :clubId
            )
            OR u.id IN (
                SELECT m.user.id FROM ClubMemberEntity m
                WHERE m.club.id = :clubId AND m.status = :status
            )
    """)
    List<UserEntity> findUsersNotInClub(@Param("clubId") Long clubId,
                                        @Param("status")ClubMemberStatus status);
}
