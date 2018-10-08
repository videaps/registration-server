package de.emobilink.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationMapper registrationMapper;

    @Autowired
    private RegistrationRepository registrationRepository;

    @PostMapping("/save")
    public void save(@RequestBody RegistrationModel registrationModel) {
        RegistrationEntity entity = registrationMapper.toRegistration(registrationModel);

        registrationRepository.save(entity);
    }

}
