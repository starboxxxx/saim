package moobean.saim.server.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import moobean.saim.server.user.domain.Physical;
import moobean.saim.server.user.domain.User;

@Entity
@Table(name = "PHYSICAL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer weight;

    private Double skeletal_muscle_mass; //골격근량

    private Double body_fat_mass; //체지방량

    private Double basal_metabolic_rate; //기초대사량

    @OneToOne(mappedBy = "physical")
    private UserEntity user;

    public static PhysicalEntity from(Physical physical) {
        PhysicalEntity physicalEntity = new PhysicalEntity();
        physicalEntity.id = physical.getId();
        physicalEntity.height = physical.getHeight();
        physicalEntity.weight = physical.getWeight();
        physicalEntity.skeletal_muscle_mass = physical.getSkeletal_muscle_mass();
        physicalEntity.body_fat_mass = physical.getBody_fat_mass();
        physicalEntity.basal_metabolic_rate = physical.getBasal_metabolic_rate();
        return physicalEntity;
    }

    public Physical toModel() {
        return Physical.builder()
                .id(id)
                .height(height)
                .weight(weight)
                .skeletalMuscleMass(skeletal_muscle_mass)
                .bodyFatMass(body_fat_mass)
                .basalMetabolicRate(basal_metabolic_rate)
                .build();
    }
}
