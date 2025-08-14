package moobean.saim.server.community.clubMember.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.community.clubMember.controller.port.CreateClubMemberService;
import moobean.saim.server.community.clubMember.controller.port.FindClubMemberService;
import moobean.saim.server.community.clubMember.controller.port.LeaveClubMemberService;
import moobean.saim.server.community.clubMember.controller.response.ClubMemberListResponse;
import moobean.saim.server.community.clubMember.controller.response.ClubMemberResponse;
import moobean.saim.server.community.clubMember.domain.ClubMember;
import moobean.saim.server.community.clubMember.domain.mapper.ClubMemberResponseMapper;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberStatus;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubRole;
import moobean.saim.server.community.clubMember.service.domain.ClubMemberService;
import moobean.saim.server.community.club.service.domain.ClubService;
import moobean.saim.server.community.clubMember.service.port.ClubMemberRepository;
import moobean.saim.server.community.follow.service.domain.FollowService;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ClubMemberErrorCode;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubMemberServiceImpl implements FindClubMemberService, CreateClubMemberService, LeaveClubMemberService {

    private final ClubService clubService;
    private final ClubMemberService clubMemberService;
    private final UserService userService;
    private final FollowService followService;
    private final ClubMemberRepository clubMemberRepository;

    @Override
    public ClubMemberListResponse findClubMembers(Long userId, Long clubId) {

        Club club = clubService.find(clubId);

        if (!clubMemberService.checkClubMember(userId, clubId)
                && !club.getShowClub()) {
            return ClubMemberResponseMapper.toSecretClubMemberListResponse();
        }

        List<ClubMember> clubMembers = clubMemberRepository.findClubMembers(clubId);

        List<ClubMemberResponse> clubMemberResponses =
                clubMembers.stream()
                        .map(member -> ClubMemberResponseMapper.toClubMemberResponse(
                                member,
                                member.getUser().getId().equals(userId), // isSame 여부
                                followService.checkFollow(userId, member.getUser().getId()) // isFollowed 여부
                        ))
                        .toList();

        return ClubMemberResponseMapper.toClubMemberListResponse(club.getShowClub(), clubMemberResponses);
    }

    @Override
    public void create(Long userId, Long clubId) {

        Club club = clubService.find(clubId);
        User user = userService.find(userId);

        ClubMember clubMember;
        if (club.getShowClub()) {
            clubMember = ClubMember.createMember(club, user, ClubMemberStatus.APPROVED, LocalDate.now());
            club.plusMemberCount();
            clubService.save(club);
        }
        else {
            clubMember = ClubMember.createMember(club, user, ClubMemberStatus.PENDING, null);
        }
        clubMemberRepository.create(clubMember);
    }

    @Override
    public void leaveClubMember(Long userId, Long clubId) {

        if (clubMemberService.isMaster(userId, clubId)) {
            throw new ApplicationException(ClubMemberErrorCode.CAPTAIN_NOT_LEAVE);
        }

        ClubMember clubMember = clubMemberRepository.findByUserAndClub(userId, clubId);
        clubMemberRepository.delete(clubMember.getId());

        Club club = clubService.find(clubId);
        club.minusMemberCount();
        clubService.save(club);
    }
}
