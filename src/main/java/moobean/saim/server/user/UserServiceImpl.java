package moobean.saim.server.user;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.user.controller.request.UpdateTrainerRequest;
import moobean.saim.server.oauth.domain.OAuthUserInfo;
import moobean.saim.server.oauth.infrastructure.RefreshTokenRepository;
import moobean.saim.server.user.controller.port.CreateUserService;
import moobean.saim.server.user.controller.port.DropUserService;
import moobean.saim.server.user.controller.port.FindUserService;
import moobean.saim.server.user.controller.port.UpdateUserService;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.domain.UserService;
import moobean.saim.server.user.service.port.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements FindUserService, CreateUserService, UpdateUserService, DropUserService {

    private final UserRepository repository;
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    public User findBySocialId(OAuthUserInfo userInfo) {
        return repository.findBySocialId(userInfo.getId())
                .orElseGet(() -> repository.save(User.create(userInfo)));
    }

    @Override
    public void drop(Long userId) {
        repository.delete(userId);
        refreshTokenRepository.delete(String.valueOf(userId));
    }

    @Override
    public void updateTrainer(Long userId, UpdateTrainerRequest request) {
        User user = userService.find(userId);

        user.updateTrainer(request.trainer());

        repository.save(user);
    }
}
