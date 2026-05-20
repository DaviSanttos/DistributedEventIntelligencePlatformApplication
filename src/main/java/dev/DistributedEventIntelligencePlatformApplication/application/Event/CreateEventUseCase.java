package dev.DistributedEventIntelligencePlatformApplication.application.Event;

import dev.DistributedEventIntelligencePlatformApplication.domain.Event.Event;
import dev.DistributedEventIntelligencePlatformApplication.domain.Event.EventRepository;
import dev.DistributedEventIntelligencePlatformApplication.domain.Event.EventStatus;

import java.util.Date;
import java.util.UUID;

public class CreateEventUseCase {
    private final EventRepository eventRepository;

    public CreateEventUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event execute(String name, String description, Date date, String location, int maxParticipants) {
        Event event = new Event(
                UUID.randomUUID(),
                name,
                description,
                date,
                location,
                maxParticipants,
                EventStatus.CREATED
        );

        eventRepository.save(event);
        return event;
    }
}
