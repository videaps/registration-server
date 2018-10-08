package de.emobilink.registration.api;

import de.emobilink.registration.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @GetMapping("/sessionUser")
    public User sessionUser() {
        return securityService.getSessionUser().orElse(null);
    }
}
