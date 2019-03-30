package com.dryPepperoniStickTeam.bePatient.domain.patient;

import com.dryPepperoniStickTeam.bePatient.domain.patient.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
