package de.emobilink.registration;

import de.emobilink.registration.security.WebSecurityConfiguration;
import de.emobilink.registration.user.User;
import de.emobilink.registration.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Import(WebSecurityConfiguration.class)
public class RegistrationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}

    @Autowired
    private UserRepository userRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(!userRepository.findByUsername("test").isPresent()) {
            userRepository.save(new User("test", passwordEncoder.encode("test123")));
        }
    }
}
