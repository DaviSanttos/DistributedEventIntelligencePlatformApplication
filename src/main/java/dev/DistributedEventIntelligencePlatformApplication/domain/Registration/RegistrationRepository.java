package dev.DistributedEventIntelligencePlatformApplication.domain.Registration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RegistrationRepository {
    void save(Registration registration);
    Optional<Registration> findById(UUID registrationId);
    List<Registration> findByEventId(UUID eventId);
    List<Registration> findByUserId(UUID userId);
    Optional<Registration> findByEventIdAndUserId(UUID eventId, UUID userId);
    long countByEventId(UUID eventId);
}
