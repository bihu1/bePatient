package com.dryPepperoniStickTeam.bePatient.domain.patient;

import com.dryPepperoniStickTeam.bePatient.common.mail.MailService;
import com.dryPepperoniStickTeam.bePatient.domain.patient.http.model.PatientDetails;
import com.dryPepperoniStickTeam.bePatient.domain.patient.model.Patient;
import com.dryPepperoniStickTeam.bePatient.domain.user.model.UserRole;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientService {

    private final PatientRepository patientRepository;
    private MailService mailService;
    private final MapperFacade mapper;

    public void register(PatientDetails patientDetails){
        Patient patient = mapper.map(patientDetails, Patient.class);
        patient.setRoles(singletonList(new UserRole("user")));
        if(patientRepository.existsByUsername(patient.getUsername())){
            throw new RuntimeException();
        }
        patientRepository.save(patient);
        if(patient.getEmail() != null){
            mailService.sendSimpleMessage(patient.getEmail(),"Rejestracja w bePatient",
                    "Gratulujemy udało Ci się pomyślnie zarejestrować, teraz możesz w pełni korzystać z naszego systemu \n bePatient Admin");
        }
    }

    public void sendMailToReception(String patientId, String message){
        mailService.sendSimpleMessage("bepatientclinic@gmail.com","Wiadomość od użytkownika","Uzytkownik"+patientId+" przesyla wiadomosc"+message);
    }
}
