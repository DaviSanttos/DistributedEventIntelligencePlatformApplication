package dev.DistributedEventIntelligencePlatformApplication.domain.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    void save(Event event);
    Optional<Event> findById(UUID eventId);
    List<Event> findAll();
    void delete(UUID eventId);
}
