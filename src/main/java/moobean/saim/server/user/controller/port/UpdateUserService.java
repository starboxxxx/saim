package moobean.saim.server.user.controller.port;

import moobean.saim.server.user.controller.request.UpdateTrainerRequest;

public interface UpdateUserService {

    void updateTrainer(Long userId, UpdateTrainerRequest request);
}
