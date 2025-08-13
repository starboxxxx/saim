package moobean.saim.server.community.club.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.club.controller.port.*;
import moobean.saim.server.community.club.controller.request.CreateClubRequest;
import moobean.saim.server.community.club.controller.request.UpdateClubRequest;
import moobean.saim.server.community.club.controller.response.ClubDetailResponse;
import moobean.saim.server.community.club.controller.response.ClubListResponse;
import moobean.saim.server.community.club.controller.response.MyClubListResponse;
import moobean.saim.server.community.club.controller.response.MyClubNameListResponse;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.community.club.domain.mapper.ClubResponseMapper;
import moobean.saim.server.community.clubMember.service.domain.ClubMemberService;
import moobean.saim.server.community.club.service.port.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements CreateClubService, FindClubService, UpdateClubService {

    private final ClubRepository clubRepository;

    private final ClubMemberService clubMemberService;

    @Override
    public ClubDetailResponse getClubDetail(Long userId, Long clubId) {

        Boolean isMember = clubMemberService.checkClubMember(userId, clubId);
        return ClubResponseMapper.toClubDetailResponse(isMember, clubRepository.find(clubId));
    }

    @Override
    public MyClubListResponse getMyClubList(Long userId) {
        List<Club> myClubs = clubRepository.getMyClubs(userId);

        List<ClubListResponse> masterClubList = new ArrayList<>();
        List<ClubListResponse> memberClubList = new ArrayList<>();

        for (Club club : myClubs) {
            ClubListResponse clubListResponse = ClubResponseMapper.toClubListResponse(club);
            if (clubMemberService.isMaster(userId, club.getId())) {
                masterClubList.add(clubListResponse);
            }
            else {
                memberClubList.add(clubListResponse);
            }
        }

        return ClubResponseMapper.toMyClubListResponse(masterClubList, memberClubList);
    }

    @Override
    public List<MyClubNameListResponse> getMyClubNameList(Long userId) {
        return clubRepository.getMyClubs(userId)
                .stream()
                .map(ClubResponseMapper::toMyClubListNameResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void createClub(Long userId, CreateClubRequest request) {

        Club club = Club.create(request);
        Club savedClub = clubRepository.save(club);

        clubMemberService.createMaster(savedClub.getId(), userId);
    }

    @Override
    public void updateClub(Long userId, Long clubId, UpdateClubRequest request) {

        clubMemberService.checkMaster(userId, clubId);
        Club club = clubRepository.find(clubId);

        club.update(request);
        clubRepository.save(club);
    }
}
