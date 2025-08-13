package moobean.saim.server.community.club.service.port;

import moobean.saim.server.community.club.domain.Club;

import java.util.List;

public interface ClubRepository {

    Club find(Long clubId);

    List<Club> getMyClubs(Long userId);

    Club save(Club club);
}
