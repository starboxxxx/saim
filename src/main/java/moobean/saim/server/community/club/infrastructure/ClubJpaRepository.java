package moobean.saim.server.community.club.infrastructure;

import moobean.saim.server.community.club.infrastructure.entity.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubJpaRepository extends JpaRepository<ClubEntity, Long> {

    @Query("SELECT c FROM ClubEntity c JOIN ClubMemberEntity cm ON c.id = cm.club.id WHERE cm.user.id = :userId")
    List<ClubEntity> findAllByUserId(@Param("approveTargetUserId") Long userId);
}
