package dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.mapper;

import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.Registration;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.entity.RegistrationJpaEntity;

public class RegistrationMapper {
    public static Registration toDomain(RegistrationJpaEntity entity) {
        return new Registration(
                entity.getRegistrationId(),
                entity.getEventId(),
                entity.getUserId(),
                entity.getRegistrationDate(),
                entity.isAttended(),
                entity.getCheckInTime()
        );
    }

    public static RegistrationJpaEntity toJpa(Registration domain) {
        return RegistrationJpaEntity.builder()
                .registrationId(domain.getRegistrationId())
                .eventId(domain.getEventId())
                .userId(domain.getUserId())
                .registrationDate(domain.getRegistrationDate())
                .attended(domain.isAttended())
                .checkInTime(domain.getCheckInTime())
                .build();
    }
}
