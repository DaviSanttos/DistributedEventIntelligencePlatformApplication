package dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.repository;

import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.entity.EventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataEventRepository extends JpaRepository<EventJpaEntity, UUID> {
}
