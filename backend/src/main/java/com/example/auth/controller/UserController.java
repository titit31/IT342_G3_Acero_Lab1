package com.example.auth.controller;

import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        // Don't modify the entity directly, better to use DTO, but for lab returning
        // entity is okay-ish if password ignored.
        // But better to return a safe DTO. Let's use JwtResponse or similar DTO without
        // token.
        // Actually, let's just return the user but make sure password isn't leaked
        // excessively or handle it.
        // User entity has password, so we should null it out or use DTO.
        // For simplicity let's just null it out here or rely on JSON ignore but I
        // didn't add @JsonIgnore.
        // I'll create a simple UserDto or just return JwtResponse without token?
        // Let's create a UserDto or just return a map/custom object.
        // For now I'll return user but set password to null.
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
}
