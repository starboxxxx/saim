package moobean.saim.server.community.clubMember.infrastructure;

import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberEntity;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClubMemberJpaRepository extends JpaRepository<ClubMemberEntity, Long> {

    @Query("SELECT m FROM ClubMemberEntity m " +
            "WHERE m.club.id = :clubId AND m.status = :status")
    List<ClubMemberEntity> findApprovedMembers(
            @Param("clubId") Long clubId,
            @Param("status") ClubMemberStatus status);

    @Query("SELECT cm FROM ClubMemberEntity cm " +
            "WHERE cm.club.id = :clubId AND cm.status = :status " +
            "ORDER BY cm.createdTime DESC")
    List<ClubMemberEntity> findPendingMembers(
            @Param("clubId") Long clubId,
            @Param("status") ClubMemberStatus status
    );

    Optional<ClubMemberEntity> findByUserIdAndClubId(Long userId, Long clubId);

    Boolean existsByClubIdAndUserId(Long clubId, Long userId);
}
