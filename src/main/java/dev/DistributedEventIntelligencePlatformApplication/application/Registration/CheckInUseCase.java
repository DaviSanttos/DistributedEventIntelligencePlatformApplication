package dev.DistributedEventIntelligencePlatformApplication.application.Registration;

import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.Registration;
import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.RegistrationRepository;

import java.util.UUID;

public class CheckInUseCase {
    private final RegistrationRepository registrationRepository;

    public CheckInUseCase(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public void execute(UUID registrationId) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        registration.checkIn();
        registrationRepository.save(registration);
    }
}
