package dev.DistributedEventIntelligencePlatformApplication.application.Registration;

import dev.DistributedEventIntelligencePlatformApplication.domain.Event.Event;
import dev.DistributedEventIntelligencePlatformApplication.domain.Event.EventRepository;
import dev.DistributedEventIntelligencePlatformApplication.domain.Event.EventStatus;
import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.Registration;
import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.RegistrationRepository;
import dev.DistributedEventIntelligencePlatformApplication.domain.User.User;
import dev.DistributedEventIntelligencePlatformApplication.domain.User.UserRole;
import dev.DistributedEventIntelligencePlatformApplication.domain.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterParticipantUseCaseTest {

    @Mock
    private RegistrationRepository registrationRepository;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private UserRepository userRepository;

    private RegisterParticipantUseCase registerParticipantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        registerParticipantUseCase = new RegisterParticipantUseCase(registrationRepository, eventRepository, userRepository);
    }

    @Test
    void shouldRegisterParticipantSuccessfully() {
        UUID eventId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Event event = new Event(eventId, "Test Event", "Description", new Date(), "Location", 10, EventStatus.CREATED);
        User user = new User(userId, "Test User", "test@example.com", "password", UserRole.PARTICIPANT);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(registrationRepository.findByEventIdAndUserId(eventId, userId)).thenReturn(Optional.empty());
        when(registrationRepository.countByEventId(eventId)).thenReturn(5L);

        Registration result = registerParticipantUseCase.execute(eventId, userId);

        assertNotNull(result);
        assertEquals(eventId, result.getEventId());
        assertEquals(userId, result.getUserId());
        verify(registrationRepository, times(1)).save(any(Registration.class));
    }

    @Test
    void shouldThrowExceptionWhenEventNotFound() {
        UUID eventId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> registerParticipantUseCase.execute(eventId, userId));
    }

    @Test
    void shouldThrowExceptionWhenAlreadyRegistered() {
        UUID eventId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Event event = new Event(eventId, "Test Event", "Description", new Date(), "Location", 10, EventStatus.CREATED);
        User user = new User(userId, "Test User", "test@example.com", "password", UserRole.PARTICIPANT);
        Registration registration = new Registration(UUID.randomUUID(), eventId, userId);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(registrationRepository.findByEventIdAndUserId(eventId, userId)).thenReturn(Optional.of(registration));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> registerParticipantUseCase.execute(eventId, userId));
        assertEquals("User already registered for this event", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNoVacancies() {
        UUID eventId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Event event = new Event(eventId, "Test Event", "Description", new Date(), "Location", 5, EventStatus.CREATED);
        User user = new User(userId, "Test User", "test@example.com", "password", UserRole.PARTICIPANT);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(registrationRepository.findByEventIdAndUserId(eventId, userId)).thenReturn(Optional.empty());
        when(registrationRepository.countByEventId(eventId)).thenReturn(5L);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> registerParticipantUseCase.execute(eventId, userId));
        assertEquals("No vacancies available for this event", exception.getMessage());
    }
}
