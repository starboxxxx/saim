package moobean.saim.server.community.club.domain.mapper;

import moobean.saim.server.community.club.controller.response.ClubDetailResponse;
import moobean.saim.server.community.club.controller.response.ClubListResponse;
import moobean.saim.server.community.club.controller.response.MyClubListResponse;
import moobean.saim.server.community.club.controller.response.MyClubNameListResponse;
import moobean.saim.server.community.club.domain.Club;

import java.util.List;

public class ClubResponseMapper {

    public static ClubDetailResponse toClubDetailResponse(Boolean isMember, Club club) {
        return new ClubDetailResponse(
                isMember,
                club.getName(),
                club.getIntroduce(),
                club.getMemberCount(),
                club.getPostCount(),
                club.getCreatedTime()
        );
    }

    public static MyClubListResponse toMyClubListResponse(List<ClubListResponse> masterClubList,
                                                          List<ClubListResponse> memberClubList) {
        return new MyClubListResponse(
                masterClubList,
                memberClubList
        );
    }

    public static ClubListResponse toClubListResponse(Club club) {
        return new ClubListResponse(
                club.getId(),
                club.getName(),
                club.getMemberCount(),
                club.getPostCount()
        );
    }

    public static MyClubNameListResponse toMyClubListNameResponse(Club club) {
        return new MyClubNameListResponse(
                club.getName()
        );
    }
}
