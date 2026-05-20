package dev.DistributedEventIntelligencePlatformApplication.infrastructure.configuration;

import dev.DistributedEventIntelligencePlatformApplication.application.Event.CreateEventUseCase;
import dev.DistributedEventIntelligencePlatformApplication.application.Registration.CheckInUseCase;
import dev.DistributedEventIntelligencePlatformApplication.application.Registration.RegisterParticipantUseCase;
import dev.DistributedEventIntelligencePlatformApplication.domain.Event.EventRepository;
import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.RegistrationRepository;
import dev.DistributedEventIntelligencePlatformApplication.domain.User.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateEventUseCase createEventUseCase(EventRepository eventRepository) {
        return new CreateEventUseCase(eventRepository);
    }

    @Bean
    public RegisterParticipantUseCase registerParticipantUseCase(
            RegistrationRepository registrationRepository,
            EventRepository eventRepository,
            UserRepository userRepository) {
        return new RegisterParticipantUseCase(registrationRepository, eventRepository, userRepository);
    }

    @Bean
    public CheckInUseCase checkInUseCase(RegistrationRepository registrationRepository) {
        return new CheckInUseCase(registrationRepository);
    }
}
