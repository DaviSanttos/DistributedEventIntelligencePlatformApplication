package dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.entity;

import dev.DistributedEventIntelligencePlatformApplication.domain.Event.EventStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "events")
public class EventJpaEntity {
    @Id
    private UUID eventId;

    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private String location;
    private int maxParticipants;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    public EventJpaEntity() {}

    public EventJpaEntity(UUID eventId, String eventName, String eventDescription, Date eventDate, String location, int maxParticipants, EventStatus eventStatus) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.eventStatus = eventStatus;
    }

    public UUID getEventId() { return eventId; }
    public void setEventId(UUID eventId) { this.eventId = eventId; }
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public String getEventDescription() { return eventDescription; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }
    public Date getEventDate() { return eventDate; }
    public void setEventDate(Date eventDate) { this.eventDate = eventDate; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }
    public EventStatus getEventStatus() { return eventStatus; }
    public void setEventStatus(EventStatus eventStatus) { this.eventStatus = eventStatus; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID eventId;
        private String eventName;
        private String eventDescription;
        private Date eventDate;
        private String location;
        private int maxParticipants;
        private EventStatus eventStatus;

        public Builder eventId(UUID eventId) { this.eventId = eventId; return this; }
        public Builder eventName(String eventName) { this.eventName = eventName; return this; }
        public Builder eventDescription(String eventDescription) { this.eventDescription = eventDescription; return this; }
        public Builder eventDate(Date eventDate) { this.eventDate = eventDate; return this; }
        public Builder location(String location) { this.location = location; return this; }
        public Builder maxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; return this; }
        public Builder eventStatus(EventStatus eventStatus) { this.eventStatus = eventStatus; return this; }

        public EventJpaEntity build() {
            return new EventJpaEntity(eventId, eventName, eventDescription, eventDate, location, maxParticipants, eventStatus);
        }
    }
}
