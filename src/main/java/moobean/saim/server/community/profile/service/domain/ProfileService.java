package moobean.saim.server.community.profile.service.domain;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.profile.domain.Profile;
import moobean.saim.server.community.profile.service.port.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public void save(Profile profile) {
        profileRepository.save(profile);
    }
}
