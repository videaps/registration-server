package de.emobilink.registration;

import java.util.ArrayList;
import java.util.List;

/**
 * Registration list wrapper to cover registration model list using
 * with registration controller.
 */
public class RegistrationList {

    private List<RegistrationModel> models = new ArrayList<>();

    public List<RegistrationModel> getModels() {
        return models;
    }

}
