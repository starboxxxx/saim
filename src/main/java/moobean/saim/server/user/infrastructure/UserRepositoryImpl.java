package moobean.saim.server.user.infrastructure;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberStatus;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.UserErrorCode;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.infrastructure.entity.UserEntity;
import moobean.saim.server.user.service.port.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> find(Long userId) {
        return userJpaRepository.findById(userId).map(UserEntity::toModel);
    }

    @Override
    public Optional<User> findBySocialId(String socialId) {
        return userJpaRepository.findBySocialId(socialId).map(UserEntity::toModel);
    }

    @Override
    public List<User> findUserNotInClub(Long clubId) {
        return userJpaRepository.findUsersNotInClub(clubId, ClubMemberStatus.PENDING)
                .stream()
                .map(UserEntity::toModel)
                .toList();
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toModel();
    }

    @Override
    public void delete(Long userId) {
        userJpaRepository.deleteById(userId);
    }
}
