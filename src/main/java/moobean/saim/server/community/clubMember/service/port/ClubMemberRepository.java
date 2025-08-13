package moobean.saim.server.community.clubMember.service.port;

import moobean.saim.server.community.clubMember.domain.ClubMember;

import java.util.List;

public interface ClubMemberRepository {

    List<ClubMember> findPendingMembers(Long clubId);

    List<ClubMember> findClubMembers(Long clubId);

    void create(ClubMember clubMember);

    void update(ClubMember updatedClubMember);

    ClubMember findByUserAndClub(Long userId, Long clubMemberId);

    Boolean checkClubMember(Long userId, Long clubId);

    void delete(Long clubMemberId);
}
