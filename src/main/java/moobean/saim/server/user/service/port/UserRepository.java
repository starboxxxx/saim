package moobean.saim.server.user.service.port;

import moobean.saim.server.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findBySocialId(String socialId);

    User save(User user);

    void delete(Long userId);
}
