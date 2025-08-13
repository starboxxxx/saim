package moobean.saim.server.community.profile.infrastructure;

import moobean.saim.server.community.profile.infrastructure.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByUserId(Long userId);
}
