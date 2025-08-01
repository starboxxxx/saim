package moobean.saim.server.user.infrastructure;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.infrastructure.entity.UserEntity;
import moobean.saim.server.user.service.port.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(UserEntity::toModel);
    }

    @Override
    public Optional<User> findBySocialId(String socialId) {
        return userJpaRepository.findBySocialId(socialId).map(UserEntity::toModel);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toModel();
    }
}
