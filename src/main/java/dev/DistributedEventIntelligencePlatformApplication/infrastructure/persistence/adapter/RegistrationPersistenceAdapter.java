package dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.adapter;

import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.Registration;
import dev.DistributedEventIntelligencePlatformApplication.domain.Registration.RegistrationRepository;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.entity.RegistrationJpaEntity;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.mapper.RegistrationMapper;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.repository.SpringDataRegistrationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RegistrationPersistenceAdapter implements RegistrationRepository {
    private final SpringDataRegistrationRepository springDataRegistrationRepository;

    public RegistrationPersistenceAdapter(SpringDataRegistrationRepository springDataRegistrationRepository) {
        this.springDataRegistrationRepository = springDataRegistrationRepository;
    }

    @Override
    public void save(Registration registration) {
        RegistrationJpaEntity entity = RegistrationMapper.toJpa(registration);
        springDataRegistrationRepository.save(entity);
    }

    @Override
    public Optional<Registration> findById(UUID registrationId) {
        return springDataRegistrationRepository.findById(registrationId)
                .map(RegistrationMapper::toDomain);
    }

    @Override
    public List<Registration> findByEventId(UUID eventId) {
        return springDataRegistrationRepository.findByEventId(eventId).stream()
                .map(RegistrationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Registration> findByUserId(UUID userId) {
        return springDataRegistrationRepository.findByUserId(userId).stream()
                .map(RegistrationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Registration> findByEventIdAndUserId(UUID eventId, UUID userId) {
        return springDataRegistrationRepository.findByEventIdAndUserId(eventId, userId)
                .map(RegistrationMapper::toDomain);
    }

    @Override
    public long countByEventId(UUID eventId) {
        return springDataRegistrationRepository.countByEventId(eventId);
    }
}
