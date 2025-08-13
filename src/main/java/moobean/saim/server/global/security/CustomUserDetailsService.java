package moobean.saim.server.global.security;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userService.find(Long.parseLong(userId));

        return new CustomUserDetails(user);
    }
}
