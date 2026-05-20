package dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "registrations")
public class RegistrationJpaEntity {
    @Id
    private UUID registrationId;

    private UUID eventId;
    private UUID userId;
    private LocalDateTime registrationDate;
    private boolean attended;
    private LocalDateTime checkInTime;

    public RegistrationJpaEntity() {}

    public RegistrationJpaEntity(UUID registrationId, UUID eventId, UUID userId, LocalDateTime registrationDate, boolean attended, LocalDateTime checkInTime) {
        this.registrationId = registrationId;
        this.eventId = eventId;
        this.userId = userId;
        this.registrationDate = registrationDate;
        this.attended = attended;
        this.checkInTime = checkInTime;
    }

    public UUID getRegistrationId() { return registrationId; }
    public void setRegistrationId(UUID registrationId) { this.registrationId = registrationId; }
    public UUID getEventId() { return eventId; }
    public void setEventId(UUID eventId) { this.eventId = eventId; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }
    public boolean isAttended() { return attended; }
    public void setAttended(boolean attended) { this.attended = attended; }
    public LocalDateTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID registrationId;
        private UUID eventId;
        private UUID userId;
        private LocalDateTime registrationDate;
        private boolean attended;
        private LocalDateTime checkInTime;

        public Builder registrationId(UUID registrationId) { this.registrationId = registrationId; return this; }
        public Builder eventId(UUID eventId) { this.eventId = eventId; return this; }
        public Builder userId(UUID userId) { this.userId = userId; return this; }
        public Builder registrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; return this; }
        public Builder attended(boolean attended) { this.attended = attended; return this; }
        public Builder checkInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; return this; }

        public RegistrationJpaEntity build() {
            return new RegistrationJpaEntity(registrationId, eventId, userId, registrationDate, attended, checkInTime);
        }
    }
}
