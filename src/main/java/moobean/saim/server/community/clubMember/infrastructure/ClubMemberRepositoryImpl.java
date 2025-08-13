package moobean.saim.server.community.clubMember.infrastructure;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.clubMember.domain.ClubMember;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberEntity;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberStatus;
import moobean.saim.server.community.clubMember.service.port.ClubMemberRepository;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ClubMemberErrorCode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ClubMemberRepositoryImpl implements ClubMemberRepository {

    private final ClubMemberJpaRepository repository;

    @Override
    public List<ClubMember> findPendingMembers(Long clubId) {
        List<ClubMemberEntity> pendingMembers = repository.findPendingMembers(clubId, ClubMemberStatus.PENDING);

        return pendingMembers.stream()
                .map(ClubMemberEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClubMember> findClubMembers(Long clubId) {
        List<ClubMemberEntity> clubMembers = repository.findApprovedMembers(clubId, ClubMemberStatus.APPROVED);

        return clubMembers.stream()
                .map(ClubMemberEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public ClubMember findByUserAndClub(Long userId, Long clubId) {
        return repository.findByUserIdAndClubId(userId, clubId)
                .orElseThrow(() -> new ApplicationException(ClubMemberErrorCode.CLUB_MEMBER_NOT_FOUND)).toModel();
    }

    @Override
    public void create(ClubMember clubMember) {
        repository.save(ClubMemberEntity.from(clubMember));
    }

    @Override
    public void update(ClubMember updateClubMember) {
        repository.save(ClubMemberEntity.from(updateClubMember));
    }

    @Override
    public Boolean checkClubMember(Long userId, Long clubId) {
        return repository.existsByClubIdAndUserId(clubId, userId);
    }

    @Override
    public void delete(Long clubMemberId) {
        repository.deleteById(clubMemberId);
    }
}
