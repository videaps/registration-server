package de.emobilink.registration.greeting;

import de.emobilink.registration.api.SecurityService;
import de.emobilink.registration.images.Image;
import de.emobilink.registration.images.ImageModel;
import de.emobilink.registration.images.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @Autowired
    private GreetingRepository greetingRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/greeting")
    public List<Greeting> getAll() {
        return this.greetingRepository.findAll();
    }

    @PostMapping("/greeting")
    public Greeting create(@RequestBody GreetingDTO greetingDTO) {
        Greeting greeting = new Greeting();
        greeting.setText(greetingDTO.text);
        greeting.setUser(securityService.getSessionUser().orElse(null));
        return greetingRepository.save(greeting);
    }

    @PostMapping("/saveImage")
    public void saveImage(@RequestBody ImageModel imageModel) {
        Image image = toImageEntity(imageModel);

        imageRepository.save(image);
    }

    @GetMapping("/readAllImages")
    public List<ImageModel> readAllImages() {
        List<Image> images = imageRepository.findAll();

        List<ImageModel> models = new ArrayList<>();
        for(Image image : images) {
            ImageModel model = toImageModel(image);
            models.add(model);
        }
        return models;
    }

    @GetMapping("/readImage")
    public ImageModel readImage(@RequestParam String name) {
        Image image = imageRepository.findByName(name);
        ImageModel model = toImageModel(image);
        return model;
    }

    private Image toImageEntity(@RequestBody ImageModel imageModel) {
        Image image = new Image();
        image.setName(imageModel.getName());
        image.setContentType(imageModel.getContentType());
        image.setData(imageModel.getData());
        return image;
    }

    private ImageModel toImageModel(Image image) {
        ImageModel model = new ImageModel();
        model.setName(image.getName());
        model.setContentType(image.getContentType());
        model.setData(image.getData());
        return model;
    }

}
