package de.emobilink.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationMapper registrationMapper;

    @Autowired
    private RegistrationRepository registrationRepository;

    /**
     * Service method to ping the server.
     *
     * @param message Message to be returned by service method.
     * @return HTTP response containing given message.
     */
    @GetMapping("/ping/{message}")
    public @ResponseBody ResponseEntity<String> ping(@PathVariable String message) {
        return new ResponseEntity<String>("Hi " + message, HttpStatus.OK);
    }

    @PostMapping("/save")
    public void save(@RequestBody RegistrationModel registrationModel) {
        RegistrationEntity entity = registrationMapper.toRegistration(registrationModel);

        registrationRepository.save(entity);
    }

}
