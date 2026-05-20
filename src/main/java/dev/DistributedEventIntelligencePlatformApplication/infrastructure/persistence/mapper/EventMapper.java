package dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.mapper;

import dev.DistributedEventIntelligencePlatformApplication.domain.Event.Event;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.entity.EventJpaEntity;

public class EventMapper {
    public static Event toDomain(EventJpaEntity entity) {
        return new Event(
                entity.getEventId(),
                entity.getEventName(),
                entity.getEventDescription(),
                entity.getEventDate(),
                entity.getLocation(),
                entity.getMaxParticipants(),
                entity.getEventStatus()
        );
    }

    public static EventJpaEntity toJpa(Event domain) {
        return EventJpaEntity.builder()
                .eventId(domain.getEventId())
                .eventName(domain.getEventName())
                .eventDescription(domain.getEventDescription())
                .eventDate(domain.getEventDate())
                .location(domain.getLocation())
                .maxParticipants(domain.getMaxParticipants())
                .eventStatus(domain.getEventStatus())
                .build();
    }
}
