package de.emobilink.registration;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Mapper(componentModel="spring")
public interface RegistrationMapper {
    RegistrationMapper MAPPER = Mappers.getMapper(RegistrationMapper.class);

    RegistrationEntity toRegistration(RegistrationModel model);

    @InheritInverseConfiguration
    RegistrationModel fromRegistration(RegistrationEntity entity);
}
