package dev.DistributedEventIntelligencePlatformApplication.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UUID userId);
    Optional<User> findByEmail(String email);
}
