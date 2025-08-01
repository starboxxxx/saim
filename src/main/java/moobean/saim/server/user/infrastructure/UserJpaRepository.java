package moobean.saim.server.user.infrastructure;

import io.lettuce.core.dynamic.annotation.Param;
import moobean.saim.server.user.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findBySocialId(String socialId);
}
