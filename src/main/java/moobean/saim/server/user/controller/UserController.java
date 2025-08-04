package moobean.saim.server.user.controller;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.global.security.CustomUserDetails;
import moobean.saim.server.user.controller.port.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal CustomUserDetails userInfo) {
        userService.delete(userInfo.user().getId());
        return ResponseEntity.noContent().build();
    }
}
