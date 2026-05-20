package dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.mapper;

import dev.DistributedEventIntelligencePlatformApplication.domain.User.User;
import dev.DistributedEventIntelligencePlatformApplication.infrastructure.persistence.entity.UserJpaEntity;

public class UserMapper {
    public static User toDomain(UserJpaEntity entity) {
        return new User(
                entity.getUserId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole()
        );
    }

    public static UserJpaEntity toJpa(User domain) {
        return UserJpaEntity.builder()
                .userId(domain.getUserId())
                .name(domain.getName())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .role(domain.getRole())
                .build();
    }
}
