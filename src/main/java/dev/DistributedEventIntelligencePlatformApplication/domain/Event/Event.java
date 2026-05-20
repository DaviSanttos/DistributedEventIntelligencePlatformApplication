package dev.DistributedEventIntelligencePlatformApplication.domain.Event;

import java.util.Date;
import java.util.UUID;

public class Event {
    private UUID eventId;
    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private String location;
    private int maxParticipants;
    private EventStatus eventStatus;

    public Event(UUID eventId, String eventName, String eventDescription, Date eventDate, String location, int maxParticipants, EventStatus eventStatus) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.eventStatus = eventStatus;
    }

    public UUID getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getLocation() {
        return location;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}
