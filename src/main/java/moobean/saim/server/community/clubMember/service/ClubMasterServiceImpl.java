package moobean.saim.server.community.clubMember.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.clubMember.controller.response.InviteMemberListResponse;
import moobean.saim.server.community.clubMember.controller.response.MasterClubMemberListResponse;
import moobean.saim.server.community.clubMember.controller.response.PendingMemberListResponse;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.community.clubMember.controller.port.*;
import moobean.saim.server.community.clubMember.controller.request.*;
import moobean.saim.server.community.clubMember.domain.ClubMember;
import moobean.saim.server.community.clubMember.domain.mapper.ClubMemberResponseMapper;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubMemberStatus;
import moobean.saim.server.community.clubMember.infrastructure.entity.ClubRole;
import moobean.saim.server.community.clubMember.service.domain.ClubMemberService;
import moobean.saim.server.community.club.service.domain.ClubService;
import moobean.saim.server.community.clubMember.service.port.ClubMemberRepository;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ClubMemberErrorCode;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubMasterServiceImpl implements FindClubMasterService, InviteClubMemberService,
        DropClubMemberService, ApproveClubMemberService, DenyClubMemberService, DelegateMasterService {

    private final ClubMemberService clubMemberService;
    private final UserService userService;
    private final ClubService clubService;

    private final ClubMemberRepository clubMemberRepository;

    @Override
    public List<PendingMemberListResponse> findPendingMemberList(Long userId, Long clubId) {
        clubMemberService.checkMaster(userId, clubId);

        List<ClubMember> pendingMembers = clubMemberRepository.findPendingMembers(clubId);

        return pendingMembers.stream()
                .map(ClubMemberResponseMapper::toPendingMemberListResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<InviteMemberListResponse> findInviteMemberList(Long userId, Long clubId) {
        clubMemberService.checkMaster(userId, clubId);
        return userService.findUserNotInClub(clubId).stream()
                .map(user -> {
                    boolean isPending = clubMemberService.checkPendingMember(user.getId(), clubId);
                    return new InviteMemberListResponse(user.getId(), user.getName(), isPending);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<MasterClubMemberListResponse> findClubMemberListByMaster(Long userId, Long clubId) {
        clubMemberService.checkMaster(userId, clubId);

        return clubMemberRepository.findClubMembers(clubId).stream()
                .map(ClubMemberResponseMapper::toMasterClubMemberListResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void inviteMember(Long userId, InviteClubMemberRequest request) {
        clubMemberService.checkMaster(userId, request.clubId());

        Club club = clubService.find(request.clubId());
        User user = userService.find(userId);

        ClubMember clubMember = ClubMember.createMember(club, user, ClubMemberStatus.APPROVED, LocalDate.now());
        clubMemberRepository.create(clubMember);
    }

    @Override
    public void dropClubMember(Long userId, DropClubMemberRequest request) {
        clubMemberService.checkMaster(userId, request.clubId());

        ClubMember clubMember = clubMemberRepository.findByUserAndClub(userId, request.clubId());

        clubMemberRepository.delete(clubMember.getId());

        Club club = clubService.find(request.clubId());
        club.minusMemberCount();
        clubService.save(club);
    }

    @Override
    public void approveClubMember(Long userId, ApproveClubMemberRequest request) {

        clubMemberService.checkMaster(userId, request.clubId());

        ClubMember clubMember = clubMemberRepository.findByUserAndClub(request.approveTargetUserId(), request.clubId());

        if (clubMember.getStatus() == ClubMemberStatus.APPROVED) {
            throw new ApplicationException(ClubMemberErrorCode.APPROVED_MEMBER);
        }

        clubMember.approveClubMember();
        clubMemberRepository.update(clubMember);

        Club club = clubService.find(request.clubId());
        club.plusMemberCount();
        clubService.save(club);
    }

    @Override
    public void denyClubMember(Long userId, DenyClubMemberRequest request) {

        clubMemberService.checkMaster(userId, request.clubId());

        ClubMember clubMember = clubMemberRepository.findByUserAndClub(request.denyTargetUserId(), request.clubId());
        clubMemberRepository.delete(clubMember.getId());
    }

    @Override
    public void delegateMaster(Long userId, DelegateMasterRequest request) {
        clubMemberService.checkMaster(userId, request.clubId());

        ClubMember master = clubMemberRepository.findByUserAndClub(userId, request.clubId());

        ClubMember targetMember = clubMemberRepository.findByUserAndClub(request.targetUserId(), request.clubId());

        master.updateClubRole(ClubRole.GENERAL);
        targetMember.updateClubRole(ClubRole.MASTER);

        clubMemberRepository.update(master);
        clubMemberRepository.update(targetMember);
    }
}
