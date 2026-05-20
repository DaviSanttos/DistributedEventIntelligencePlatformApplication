package dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.adapter;

import dev.DistributedEventIntelligencePlatformApplication.domain.Event.Event;
import dev.DistributedEventIntelligencePlatformApplication.domain.Event.EventRepository;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.entity.EventJpaEntity;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.mapper.EventMapper;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.repository.SpringDataEventRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EventPersistenceAdapter implements EventRepository {
    private final SpringDataEventRepository springDataEventRepository;

    public EventPersistenceAdapter(SpringDataEventRepository springDataEventRepository) {
        this.springDataEventRepository = springDataEventRepository;
    }

    @Override
    public void save(Event event) {
        EventJpaEntity entity = EventMapper.toJpa(event);
        springDataEventRepository.save(entity);
    }

    @Override
    public Optional<Event> findById(UUID eventId) {
        return springDataEventRepository.findById(eventId)
                .map(EventMapper::toDomain);
    }

    @Override
    public List<Event> findAll() {
        return springDataEventRepository.findAll().stream()
                .map(EventMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID eventId) {
        springDataEventRepository.deleteById(eventId);
    }
}
