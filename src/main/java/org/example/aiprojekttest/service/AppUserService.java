
package org.example.aiprojekttest.service;

import org.example.aiprojekttest.model.AppUser;
import org.example.aiprojekttest.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser createUser(String username) {
        AppUser user = new AppUser(username);
        return appUserRepository.save(user);
    }

    public AppUser getUserById(long userId) {
        return appUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser saveUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public void deleteUser(long userId) {
        appUserRepository.deleteById(userId);
    }
}
