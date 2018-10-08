package de.emobilink.registration;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Registration repository to deal with all registration form data issues.
 */
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long> {

}
