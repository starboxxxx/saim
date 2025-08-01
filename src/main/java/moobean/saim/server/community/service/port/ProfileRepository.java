package moobean.saim.server.community.service.port;

import moobean.saim.server.community.domain.Profile;
import moobean.saim.server.community.infrastructure.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileRepository {

    Optional<Profile> findByUserId(Long userId);

    Void save(Profile profile);
}
