package com.dryPepperoniStickTeam.bePatient.domain.patient;

import com.dryPepperoniStickTeam.bePatient.domain.patient.http.model.PatientDetails;
import com.dryPepperoniStickTeam.bePatient.domain.patient.model.Patient;
import com.dryPepperoniStickTeam.bePatient.domain.user.UserRole;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientService {

    private final PatientRepository patientRepository;
    private final MapperFacade mapper;

    public void register(PatientDetails patientDetails){
        Patient patient = mapper.map(patientDetails, Patient.class);
        patient.setRoles(asList(new UserRole("user")));
        patientRepository.save(patient);
    }
}
