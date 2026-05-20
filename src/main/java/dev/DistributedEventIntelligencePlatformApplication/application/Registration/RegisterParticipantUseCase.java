package dev.DistributedEventIntelligencePlatformApplication.application.Registration;

import dev.DistributedEventIntelligencePlatformApplication.domain.Event.Event;
import dev.DistributedEventIntelligencePlatformApplication.domain.Event.EventRepository;
import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.Registration;
import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.RegistrationRepository;
import dev.DistributedEventIntelligencePlatformApplication.domain.User.User;
import dev.DistributedEventIntelligencePlatformApplication.domain.User.UserRepository;

import java.util.UUID;

public class RegisterParticipantUseCase {
    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public RegisterParticipantUseCase(RegistrationRepository registrationRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Registration execute(UUID eventId, UUID userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // RN01: Um participante não pode se inscrever duas vezes no mesmo evento.
        registrationRepository.findByEventIdAndUserId(eventId, userId).ifPresent(r -> {
            throw new RuntimeException("User already registered for this event");
        });

        // RN02: O número de inscritos não pode ultrapassar o limite de vagas.
        long currentRegistrations = registrationRepository.countByEventId(eventId);
        if (currentRegistrations >= event.getMaxParticipants()) {
            throw new RuntimeException("No vacancies available for this event");
        }

        Registration registration = new Registration(
                UUID.randomUUID(),
                eventId,
                userId
        );

        registrationRepository.save(registration);
        return registration;
    }
}
