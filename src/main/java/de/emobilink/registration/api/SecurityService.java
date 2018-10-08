package de.emobilink.registration.api;

import de.emobilink.registration.security.AppUserDetails;
import de.emobilink.registration.user.User;
import de.emobilink.registration.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getSessionUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof AppUserDetails) {
            String username = ((AppUserDetails) principal).getUsername();

            return userRepository.findByUsername(username);
        }

        return Optional.empty();
    }
}
