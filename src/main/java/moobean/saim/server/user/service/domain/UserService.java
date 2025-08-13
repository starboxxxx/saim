package moobean.saim.server.user.service.domain;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.FollowErrorCode;
import moobean.saim.server.global.exception.code.UserErrorCode;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.port.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User find(Long userId) {
        return userRepository.find(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));
    }

    public User findUserInFollowService(Long userId, FollowErrorCode followErrorCode) {
        return userRepository.find(userId)
                .orElseThrow(() -> new ApplicationException(followErrorCode));
    }

    public List<User> findUserNotInClub(Long clubId) {
        return userRepository.findUserNotInClub(clubId);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
