package de.emobilink.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/registration")
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
    public @ResponseBody
    ResponseEntity<String> ping(@PathVariable String message) {
        return new ResponseEntity<String>("Hi " + message, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:4200"})
    @PostMapping("/save")
    public void save(@RequestBody RegistrationModel registrationModel) {
        RegistrationEntity entity = registrationMapper.toRegistration(registrationModel);

        registrationRepository.save(entity);
    }

    @GetMapping("/readAll")
    public RegistrationList readAll() {
        List<RegistrationEntity> entities = registrationRepository.findAll();
        RegistrationList registrations = convert(entities);
        return registrations;
    }

    @PostMapping("/readAllByEmail")
    public RegistrationList readAllByEmail(@RequestBody String email) {
        List<RegistrationEntity> entities = registrationRepository.findAllByEmail(email);
        RegistrationList registrations = convert(entities);
        return registrations;
    }

    /*
     * Convert a list of entities to a registration list wrapper.
     */
    private RegistrationList convert(List<RegistrationEntity> entities) {
        RegistrationList registrations = new RegistrationList();
        for (RegistrationEntity entity : entities) {
            RegistrationModel model = registrationMapper.fromRegistration(entity);
            registrations.getModels().add(model);
        }
        return registrations;
    }

}
