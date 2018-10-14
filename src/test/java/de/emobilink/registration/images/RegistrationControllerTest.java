package de.emobilink.registration.images;

import de.emobilink.registration.RegistrationApplication;
import de.emobilink.registration.RegistrationList;
import de.emobilink.registration.RegistrationModel;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class RegistrationControllerTest {

    @LocalServerPort
    private int port;

    private String url;

    @Before
    public void url() {
        url = "http://localhost:" + port + "/registration";
    }

    @Test
    public void save() {
        RestTemplate restTemplate = new RestTemplate();

        RegistrationModel model = createRegistrationModel("oliver.hock@gmail.com");
        restTemplate.postForObject(url + "/save", model, Void.class);
    }

    @Test
    public void readAllByEmail() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForObject(
                url + "/save", createRegistrationModel("oliver.hock@gmail.com"), Void.class);
        restTemplate.postForObject(
                url + "/save", createRegistrationModel("oliver.hock@gmail.com"), Void.class);

        RegistrationList registrations = restTemplate.postForObject(
                url + "/readAllByEmail", "oliver.hock@gmail.com", RegistrationList.class);

        assertTrue(registrations.getModels().size() >= 2);
    }

    @Test
    public void readAll() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForObject(
                url + "/save", createRegistrationModel("oliver.hock@gmail.com"), Void.class);
        restTemplate.postForObject(
                url + "/save", createRegistrationModel("oliver.hock@videa.services"), Void.class);

        RegistrationList registrations = restTemplate.getForObject(
                url + "/readAll", RegistrationList.class);

        assertTrue(registrations.getModels().size() >= 2);
    }

    private RegistrationModel createRegistrationModel(String email) {
        RegistrationModel model = new RegistrationModel();
        model.setFirstName("Oli");
        model.setSurName("Hock");
        model.setEmail(email);
        model.setStreet("Bergstraße");
        model.setHouseNo("12");
        model.setPostCode("59269");
        model.setCity("Beckum");
        model.setPreDial("0176");
        model.setPhoneNumber("29499727");
        model.setDrivingLicense("driving license");
        model.setTariff("Normal");
        model.setDiscount("RVM-Abo");
        model.setReference("13081971");
        model.setTermsAndConditionsAccepted(true);
        model.setPrivacyPolicyAccepted(true);
        model.setPriceListAccepted(true);
        return model;
    }

}
