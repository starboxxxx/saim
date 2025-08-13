package moobean.saim.server.community.club.infrastructure;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.community.club.infrastructure.entity.ClubEntity;
import moobean.saim.server.community.club.service.port.ClubRepository;
import moobean.saim.server.community.follow.domain.Follow;
import moobean.saim.server.community.follow.infrastructure.entity.FollowEntity;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ClubErrorCode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepository {

    private final ClubJpaRepository jpaRepository;

    @Override
    public Club find(Long clubId) {
        ClubEntity clubEntity = jpaRepository.findById(clubId)
                .orElseThrow(() -> new ApplicationException(ClubErrorCode.CLUB_NOT_FOUND));

        return clubEntity.toModel();
    }

    @Override
    public List<Club> getMyClubs(Long userId) {

        List<ClubEntity> clubEntities = jpaRepository.findAllByUserId(userId);
        return clubEntities.stream()
                    .map(ClubEntity::toModel)
                    .collect(Collectors.toList());
    }


    @Override
    public Club save(Club club) {

        return jpaRepository.save(ClubEntity.from(club)).toModel();
    }
}
