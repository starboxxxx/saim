package moobean.saim.server.community.profile.domain.mapper;

import moobean.saim.server.community.profile.controller.response.MyPageProfileResponse;
import moobean.saim.server.community.profile.controller.response.ProfileResponse;
import moobean.saim.server.community.profile.controller.response.TrainerResponse;
import moobean.saim.server.community.profile.domain.Profile;
import moobean.saim.server.user.domain.User;

public class ProfileResponseMapper {

    public static ProfileResponse toProfileResponse(Profile profile, Boolean isSame, Boolean isFollowed) {
        return new ProfileResponse(
                profile.getId(),
                isSame,
                isFollowed,
                profile.getPostNum(),
                profile.getFollowerNum(),
                profile.getFollowingNum(),
                profile.getIntroduce()
        );
    }

    public static MyPageProfileResponse toMyPageProfileResponse(Profile profile, User user) {
        return new MyPageProfileResponse(
                profile.getId(),
                user.getName(),
                user.getEmail(),
                profile.getPostNum(),
                profile.getFollowerNum(),
                profile.getFollowingNum(),
                profile.getIntroduce()
        );
    }

    public static TrainerResponse toTrainerResponse(User user) {
        return new TrainerResponse(
                user.getTrainer().getDescription()
        );
    }

}
