package moobean.saim.server.user.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import moobean.saim.server.user.infrastructure.entity.Trainer;

public record UpdateTrainerRequest(
        @Schema(description = "수정할 트레이너", example = "SOFIA")
        Trainer trainer
) {
}
