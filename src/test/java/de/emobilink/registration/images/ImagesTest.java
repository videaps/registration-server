package de.emobilink.registration.images;

import de.emobilink.registration.ServerApplication;
import org.apache.commons.io.IOUtils;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class ImagesTest {

    @LocalServerPort
    private int port;

    private String url;

    @Before
    public void url() {
        url = "http://localhost:" + port + "/api";
    }

    /*
     * Read a test image vom test resource folder.
     */
    private byte[] readImage(String fileName) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        return IOUtils.toByteArray(inputStream);
    }

    @Test
    public void readAllImages() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        ImageModel model = createImageModel();
        restTemplate.postForObject(url + "/saveImage", model, Void.class);

        ImageModel[] models = restTemplate.getForObject(url + "/readAllImages", ImageModel[].class);
        assertNotNull(models);
        assertTrue(models.length > 0);
    }

    @Test
    public void saveImage() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        ImageModel model = createImageModel();
        restTemplate.postForObject(url + "/saveImage", model, Void.class);

        ImageModel[] models = new RestTemplate().getForObject(url + "/readAllImages", ImageModel[].class);
        assertNotNull(models);
        assertTrue(models.length > 1);
    }

    private ImageModel createImageModel() throws IOException {
        String fileName = "images-test-01.jpg";
        ImageModel model = new ImageModel();
        model.setName(fileName);
        model.setContentType("image/jpeg");
        model.setData(Arrays.toString(readImage(fileName)));
        return model;
    }

    @Test
    public void readImage() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        String name = "images-test-01.jpg";

        ImageModel model = new ImageModel();
        model.setName(name);
        model.setContentType("image/jpeg");

        model.setData(Arrays.toString(readImage(name)));

        restTemplate.postForObject(url + "/saveImage", model, Void.class);

        ImageModel imageModel = restTemplate.getForObject(url + "/readImage" + "?name=" + name, ImageModel.class);
        assertNotNull(imageModel);

    }

}
