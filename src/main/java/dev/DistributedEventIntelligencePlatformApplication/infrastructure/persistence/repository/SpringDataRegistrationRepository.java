package dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.repository;

import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.entity.RegistrationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataRegistrationRepository extends JpaRepository<RegistrationJpaEntity, UUID> {
    List<RegistrationJpaEntity> findByEventId(UUID eventId);
    List<RegistrationJpaEntity> findByUserId(UUID userId);
    Optional<RegistrationJpaEntity> findByEventIdAndUserId(UUID eventId, UUID userId);
    long countByEventId(UUID eventId);
}
