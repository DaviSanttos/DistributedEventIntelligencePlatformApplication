package dev.DistributedEventIntelligencePlatformApplication.presentation.Event;

import dev.DistributedEventIntelligencePlatformApplication.application.Event.CreateEventUseCase;
import dev.DistributedEventIntelligencePlatformApplication.domain.Event.Event;
import dev.DistributedEventIntelligencePlatformApplication.presentation.dto.CreateEventRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Eventos", description = "Endpoints para gerenciamento de eventos")
public class EventController {
    private final CreateEventUseCase createEventUseCase;

    public EventController(CreateEventUseCase createEventUseCase) {
        this.createEventUseCase = createEventUseCase;
    }

    @PostMapping
    @Operation(summary = "Criar um novo evento", description = "Cadastra um novo evento no sistema com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
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
