package dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.adapter;

import dev.DistributedEventIntelligencePlatformApplication.domain.User.User;
import dev.DistributedEventIntelligencePlatformApplication.domain.User.UserRepository;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.entity.UserJpaEntity;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.mapper.UserMapper;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.repository.SpringDataUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserPersistenceAdapter implements UserRepository {
    private final SpringDataUserRepository springDataUserRepository;

    public UserPersistenceAdapter(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public void save(User user) {
        UserJpaEntity entity = UserMapper.toJpa(user);
        springDataUserRepository.save(entity);
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return springDataUserRepository.findById(userId)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email)
                .map(UserMapper::toDomain);
    }
}
