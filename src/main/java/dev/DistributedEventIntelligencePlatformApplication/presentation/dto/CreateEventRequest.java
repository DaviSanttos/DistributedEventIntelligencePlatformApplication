package dev.DistributedEventIntelligencePlatformApplication.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

@Schema(description = "Requisição para criação de um novo evento")
public class CreateEventRequest {
    
    @Schema(description = "Nome do evento", example = "Workshop de Clean Architecture", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    
    @Schema(description = "Descrição detalhada do evento", example = "Um evento prático sobre como implementar Clean Architecture em Java.")
    private String description;
    
    @Schema(description = "Data e hora de realização do evento", example = "2026-05-20T09:00:00Z", requiredMode = Schema.RequiredMode.REQUIRED)
    private Date date;
    
    @Schema(description = "Local ou link da reunião", example = "Auditório Principal ou Zoom Link", requiredMode = Schema.RequiredMode.REQUIRED)
    private String location;
    
    @Schema(description = "Capacidade máxima de participantes", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
    private int maxParticipants;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }
}
