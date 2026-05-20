package dev.DistributedEventIntelligencePlatformApplication.domain.User;

import java.util.UUID;

public class User {
    private UUID userId;
    private String name;
    private String email;
    private String password; // In a real system, this would be a hash
    private UserRole role;

    public User(UUID userId, String name, String email, String password, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}
