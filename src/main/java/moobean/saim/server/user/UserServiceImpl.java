package moobean.saim.server.user;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.UserErrorCode;
import moobean.saim.server.user.controller.port.UserService;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.port.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public void delete(Long userId) {
        repository.delete(userId);
    }
}
