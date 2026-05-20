package dev.DistributedEventIntelligencePlatformApplication.presentation.Registration;

import dev.DistributedEventIntelligencePlatformApplication.application.Registration.CheckInUseCase;
import dev.DistributedEventIntelligencePlatformApplication.application.Registration.RegisterParticipantUseCase;
import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.Registration;
import dev.DistributedEventIntelligencePlatformApplication.presentation.dto.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    private final RegisterParticipantUseCase registerParticipantUseCase;
    private final CheckInUseCase checkInUseCase;

    public RegistrationController(RegisterParticipantUseCase registerParticipantUseCase, CheckInUseCase checkInUseCase) {
        this.registerParticipantUseCase = registerParticipantUseCase;
        this.checkInUseCase = checkInUseCase;
    }

    @PostMapping
    public ResponseEntity<Registration> register(@RequestBody RegistrationRequest request) {
        Registration registration = registerParticipantUseCase.execute(
                request.getEventId(),
                request.getUserId()
        );
        return ResponseEntity.ok(registration);
    }

    @PostMapping("/{registrationId}/check-in")
    public ResponseEntity<Void> checkIn(@PathVariable UUID registrationId) {
        checkInUseCase.execute(registrationId);
        return ResponseEntity.noContent().build();
    }
}
