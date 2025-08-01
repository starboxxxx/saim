package moobean.saim.server.community.infrastructure;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.domain.Profile;
import moobean.saim.server.community.infrastructure.entity.ProfileEntity;
import moobean.saim.server.community.service.port.ProfileRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepository {

    private final ProfileJpaRepository repository;

    @Override
    public Optional<Profile> findByUserId(Long userId) {
        return repository.findByUserId(userId).map(ProfileEntity::toModel);
    }

    @Override
    public Void save(Profile profile) {
        ProfileEntity profileEntity = ProfileEntity.from(profile);
        repository.save(profileEntity);
        return null;
    }
}
