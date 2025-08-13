package moobean.saim.server.user.service.port;

import moobean.saim.server.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> find(Long id);

    Optional<User> findBySocialId(String socialId);

    List<User> findUserNotInClub(Long clubId);

    User save(User user);

    void delete(Long userId);
}
