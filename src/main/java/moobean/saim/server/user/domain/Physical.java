package moobean.saim.server.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Physical {

    private final Long id;

    private final Integer height;

    private final Integer weight;

    private final Double skeletal_muscle_mass; //골격근량

    private final Double body_fat_mass; //체지방량

    private final Double basal_metabolic_rate;

    private User user;

    @Builder
    public Physical(Long id, Integer height,
                    Integer weight, Double skeletalMuscleMass,
                    Double bodyFatMass, Double basalMetabolicRate) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        skeletal_muscle_mass = skeletalMuscleMass;
        body_fat_mass = bodyFatMass;
        basal_metabolic_rate = basalMetabolicRate;
    }

    public static Physical create() {
        return Physical.builder()
                .height(0)
                .weight(0)
                .skeletalMuscleMass(0.0)
                .bodyFatMass(0.0)
                .basalMetabolicRate(0.0)
                .build();
    }

    public void updateUser(User user) {
        this.user = user;
    }
}
