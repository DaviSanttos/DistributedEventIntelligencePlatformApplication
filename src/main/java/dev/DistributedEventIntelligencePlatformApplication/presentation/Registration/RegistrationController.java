package dev.DistributedEventIntelligencePlatformApplication.presentation.Registration;

import dev.DistributedEventIntelligencePlatformApplication.application.Registration.CheckInUseCase;
import dev.DistributedEventIntelligencePlatformApplication.application.Registration.RegisterParticipantUseCase;
import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.Registration;
import dev.DistributedEventIntelligencePlatformApplication.presentation.dto.RegistrationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/registrations")
@Tag(name = "Inscrições", description = "Endpoints para gerenciamento de inscrições e check-in")
public class RegistrationController {
    private final RegisterParticipantUseCase registerParticipantUseCase;
    private final CheckInUseCase checkInUseCase;

    public RegistrationController(RegisterParticipantUseCase registerParticipantUseCase, CheckInUseCase checkInUseCase) {
        this.registerParticipantUseCase = registerParticipantUseCase;
        this.checkInUseCase = checkInUseCase;
    }

    @PostMapping
    @Operation(summary = "Inscrever participante", description = "Realiza a inscrição de um usuário em um evento específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscrição realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação (ex: sem vagas ou já inscrito)"),
            @ApiResponse(responseCode = "404", description = "Evento ou Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Registration> register(@RequestBody RegistrationRequest request) {
        Registration registration = registerParticipantUseCase.execute(
                request.getEventId(),
                request.getUserId()
        );
        return ResponseEntity.ok(registration);
    }

    @PostMapping("/{registrationId}/check-in")
    @Operation(summary = "Realizar check-in", description = "Registra a presença de um participante no evento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Check-in realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Inscrição não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> checkIn(@PathVariable UUID registrationId) {
        checkInUseCase.execute(registrationId);
        return ResponseEntity.noContent().build();
    }
}
