package moobean.saim.server.community.club.service.domain;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.community.club.domain.mapper.ClubResponseMapper;
import moobean.saim.server.community.club.service.port.ClubRepository;
import moobean.saim.server.community.clubMember.service.domain.ClubMemberService;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ClubErrorCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public Club find(Long clubId) {
        return clubRepository.find(clubId);
    }

    public void save(Club club) {
        clubRepository.save(club);
    }

    public Boolean checkAuthority(Boolean isMember, Long clubId) {
        Club club = clubRepository.find(clubId);

        return isMember || club.getShowClub();
    }
}
