package moobean.saim.server.community.clubMember.service.domain;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.community.clubMember.domain.ClubMember;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberStatus;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubRole;
import moobean.saim.server.community.club.service.domain.ClubService;
import moobean.saim.server.community.clubMember.service.port.ClubMemberRepository;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ClubMemberErrorCode;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.domain.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubMemberService {

    private final ClubMemberRepository clubMemberRepository;
    private final ClubService clubService;
    private final UserService userService;

    public Boolean isMaster(Long userId, Long clubId) {
        ClubMember clubMember = clubMemberRepository.findByUserAndClub(userId, clubId);

        return clubMember.getClubRole() == ClubRole.MASTER;
    }

    public Boolean checkPendingMember(Long userId, Long clubId) {
        Boolean isMember = clubMemberRepository.checkClubMember(userId, clubId);

        if (!isMember) {
            return false;
        }
        else {
            ClubMember clubMember = clubMemberRepository.findByUserAndClub(userId, clubId);
            return clubMember.getStatus() == ClubMemberStatus.PENDING;
        }
    }

    public void createMaster(Long clubId, Long userId) {
        Club club = clubService.find(clubId);
        User user = userService.find(userId);
        ClubMember clubMember = ClubMember.createMaster(club, user);
        clubMemberRepository.create(clubMember);
    }

    public void checkMaster(Long userId, Long clubId) {
        ClubMember clubMember = clubMemberRepository.findByUserAndClub(userId, clubId);

        if (!(clubMember.getClubRole() == ClubRole.MASTER)) {
            throw new ApplicationException(ClubMemberErrorCode.NOT_CAPTAIN);
        }
    }

    public Boolean checkClubMember(Long userId, Long clubId) {
        return clubMemberRepository.checkClubMember(userId, clubId);
    }
}
