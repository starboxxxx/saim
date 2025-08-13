package moobean.saim.server.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.user.controller.request.UpdateTrainerRequest;
import moobean.saim.server.global.security.CustomUserDetails;
import moobean.saim.server.user.controller.port.DropUserService;
import moobean.saim.server.user.controller.port.UpdateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "02. User")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final DropUserService dropUserService;
    private final UpdateUserService updateUserService;

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal CustomUserDetails userInfo) {
        dropUserService.drop(userInfo.user().getId());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "트레이너 수정")
    @PatchMapping("/trainer")
    public ResponseEntity<Void> updateTrainer(@AuthenticationPrincipal CustomUserDetails userInfo,
                                              @RequestBody UpdateTrainerRequest request) {
        updateUserService.updateTrainer(userInfo.user().getId(), request);
        return ResponseEntity.noContent().build();
    }
}
