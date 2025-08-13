package moobean.saim.server.community.profile.service.port;

import moobean.saim.server.community.profile.domain.Profile;

import java.util.Optional;

public interface ProfileRepository {

    Optional<Profile> findByUserId(Long userId);

    Void save(Profile profile);
}
