package moobean.saim.server.community.controller.request;

import moobean.saim.server.user.infrastructure.entity.Trainer;

public record UpdateTrainerRequest(
        Trainer trainer
) {
}
