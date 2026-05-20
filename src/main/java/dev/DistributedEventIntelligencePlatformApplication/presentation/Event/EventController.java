package dev.DistributedEventIntelligencePlatformApplication.presentation.Event;

import dev.DistributedEventIntelligencePlatformApplication.application.Event.CreateEventUseCase;
import dev.DistributedEventIntelligencePlatformApplication.domain.Event.Event;
import dev.DistributedEventIntelligencePlatformApplication.presentation.dto.CreateEventRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final CreateEventUseCase createEventUseCase;

    public EventController(CreateEventUseCase createEventUseCase) {
        this.createEventUseCase = createEventUseCase;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventRequest request) {
        Event event = createEventUseCase.execute(
                request.getName(),
                request.getDescription(),
                request.getDate(),
                request.getLocation(),
                request.getMaxParticipants()
        );
        return ResponseEntity.ok(event);
    }
}
