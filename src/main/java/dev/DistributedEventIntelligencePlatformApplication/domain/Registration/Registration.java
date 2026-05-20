package dev.DistributedEventIntelligencePlatformApplication.domain.Registration;

import java.time.LocalDateTime;
import java.util.UUID;

public class Registration {
    private UUID registrationId;
    private UUID eventId;
    private UUID userId;
    private LocalDateTime registrationDate;
    private boolean attended;
    private LocalDateTime checkInTime;

    public Registration(UUID registrationId, UUID eventId, UUID userId) {
        this.registrationId = registrationId;
        this.eventId = eventId;
        this.userId = userId;
        this.registrationDate = LocalDateTime.now();
        this.attended = false;
    }

    public Registration(UUID registrationId, UUID eventId, UUID userId, LocalDateTime registrationDate, boolean attended, LocalDateTime checkInTime) {
        this.registrationId = registrationId;
        this.eventId = eventId;
        this.userId = userId;
        this.registrationDate = registrationDate;
        this.attended = attended;
        this.checkInTime = checkInTime;
    }

    public void checkIn() {
        this.attended = true;
        this.checkInTime = LocalDateTime.now();
    }

    public UUID getRegistrationId() {
        return registrationId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public UUID getUserId() {
        return userId;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public boolean isAttended() {
        return attended;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }
}
